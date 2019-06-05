package ua.smolii.scaggregator.service.illusione;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.smolii.scaggregator.domain.Coffee;
import ua.smolii.scaggregator.repsitories.CoffeeRepository;
import ua.smolii.scaggregator.service.Keyword;
import ua.smolii.scaggregator.service.PhraseFinder;
import ua.smolii.scaggregator.service.Roaster;

@Service
@Slf4j
@AllArgsConstructor(onConstructor_={@Autowired})
public class Illusione implements Roaster {

	private final PhraseFinder phraseFinder;
	private final IllusioneKeywordConverter keywordConverter;
	private final CoffeeRepository repository;

	@Override
	@Transactional(noRollbackFor = IOException.class)
	public void populateCatalog() {
		try {
			Document document = Jsoup.connect("http://illusione.ua/").get();
			Elements products = document.getElementsByClass("product");
			products.stream()
					.map(product -> {
						Element link = product.getElementsByTag("a").get(0);
						return link.attr("href");
					})
					.map(this::parseProduct)
					.collect(Collectors.toList())
					.forEach(repository::save);
		}
		catch (IOException e) {
			log.error("IOException while populating catalog for: {}", getSimpleName(), e);
		}
	}

	private Coffee parseProduct(String url) {
		try {
			Document productPage = Jsoup.connect(url.replace("https", "http")).get();
			Element description = productPage.getElementsByClass("woocommerce-product-details__short-description").get(0);
			Map<Keyword, String> parsedData = new HashMap<>();
			description.getElementsByTag("p").forEach(element -> {
				String text = element.text();
				log.info("Text: {}",  text);
				Optional<Pair<Keyword, String>> keyword = phraseFinder.findKeyword(text);
				keyword.ifPresent(pair -> parsedData.put(pair.getFirst(), pair.getSecond()));
			});
			return keywordConverter.toEntity(parsedData, Collections.emptyMap() /* todo */, getSimpleName());
		}
		catch (IOException e) {
			log.error("Error while getting product from url: {}", url);
			throw new RuntimeException(e);
		}
	}

	private String getSimpleName() {
		return this.getClass().getSimpleName();
	}
}
