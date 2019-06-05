package ua.smolii.scaggregator.service.illusione;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ua.smolii.scaggregator.domain.Coffee;
import ua.smolii.scaggregator.domain.Pack;
import ua.smolii.scaggregator.domain.RoastType;
import ua.smolii.scaggregator.service.Keyword;

@Component
public class IllusioneKeywordConverter {

	public Coffee toEntity(Map<Keyword, String> parsedData, Map<Integer, BigDecimal> weightToPrice, String roaster) {
		Coffee coffee = new Coffee();
		coffee.setName(parsedData.get(Keyword.NAME));
		coffee.setDescription(parsedData.get(Keyword.DESCRIPTION));
		coffee.setRoaster(roaster);
		coffee.setTasteProfile(removeKeywordNullable(Keyword.TASTE_PROFILE, parsedData.get(Keyword.TASTE_PROFILE)));
		coffee.setBalance(removeKeywordNullable(Keyword.BALANCE, parsedData.get(Keyword.BALANCE)));
//		coffee.setOriginCountry(Country.fromWording(parsedData.get(Keyword.COUNTRY)));
		coffee.setOriginRegion(removeKeywordNullable(Keyword.REGION, parsedData.get(Keyword.REGION)));
		coffee.setVariety(removeKeywordNullable(Keyword.VARIETY, parsedData.get(Keyword.VARIETY)));
		coffee.setProcessing(removeKeywordNullable(Keyword.PROCESSING, parsedData.get(Keyword.PROCESSING)));
//		coffee.setYear(
//				parsedData.get(Keyword.YEAR) != null
//						? Integer.valueOf(removeKeyword(Keyword.YEAR, parsedData.get(Keyword.YEAR)))
//						: null
//		);
		coffee.setPacks(toPacks(weightToPrice));
		coffee.setRoastType(
				parsedData.get(Keyword.ROAST_TYPE) != null
						? RoastType.fromWording(removeKeyword(Keyword.ROAST_TYPE, parsedData.get(Keyword.ROAST_TYPE)))
						: null
		);
		coffee.setScaRate(
				parsedData.get(Keyword.SCA_RATE) != null
						? new BigDecimal(removeKeyword(Keyword.SCA_RATE, parsedData.get(Keyword.SCA_RATE)))
						: null);
		// todo image

		return coffee;
	}

	private List<Pack> toPacks(Map<Integer, BigDecimal> weightToPrice) {
		return weightToPrice.entrySet().stream()
				.map(entry -> new Pack(entry.getValue(), entry.getKey()))
				.collect(toList());
	}

	private String removeKeywordNullable(Keyword keyword, String value) {
		return Optional.ofNullable(value).map(val -> removeKeyword(keyword, val)).orElse(null);
	}

	private String removeKeyword(Keyword keyword, String value) {
		String newValue = value;
		for (String k : keyword.getWords()) {
			newValue = newValue.replace(k + ": ", "");
		}
		return value;
	}
}
