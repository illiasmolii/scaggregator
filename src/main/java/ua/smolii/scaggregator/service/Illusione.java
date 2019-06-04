package ua.smolii.scaggregator.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor(onConstructor_={@Autowired})
public class Illusione implements Roaster {

	private final PhraseFinder phraseFinder;
	private final KeywordConverter keywordConverter;

	@Override
	public void populateCatalog() {
		try {
			Document document = Jsoup.connect("https://illusione.ua/").get();
			Elements products = document.getElementsByClass("product");
			products.stream()
					.map(product -> {
						Element link = product.getElementsByTag("a").get(0);
						return link.attr("href");
					}).forEach(this::parseProduct);
		}
		catch (IOException e) {
			log.error("IOException while populating catalog for: {}", this.getClass().getSimpleName());
		}
	}

	private void parseProduct(String url) {
		try {
			Document productPage = Jsoup.connect(url).get();
			Element description = productPage.getElementsByClass("woocommerce-product-details__short-description").get(0);
			Map<Keyword, String> parsedData = new HashMap<>();
			description.getElementsByTag("p").forEach(element -> {
				String text = element.text();
				Optional<Pair<Keyword, String>> keyword = phraseFinder.findKeyword(text);
				keyword.ifPresent(pair -> parsedData.put(pair.getFirst(), pair.getSecond()));
			});

		}
		catch (IOException e) {
			log.error("Error while getting product from url: {}", url);
		}
	}
}
