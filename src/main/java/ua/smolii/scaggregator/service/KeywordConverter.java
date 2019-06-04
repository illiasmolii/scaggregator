package ua.smolii.scaggregator.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import ua.smolii.scaggregator.domain.Coffee;
import ua.smolii.scaggregator.domain.Country;
import ua.smolii.scaggregator.domain.RoastType;

@Component
public class KeywordConverter {

	public Coffee toEntity(Map<Keyword, String> parsedData, String roaster, Country country, RoastType roastType) {
		Coffee coffee = new Coffee();
		coffee.setName(parsedData.get(Keyword.NAME));
		coffee.setDescription(parsedData.get(Keyword.DESCRIPTION));
		coffee.setRoaster(roaster);
		coffee.setTasteProfile(parsedData.get(Keyword.TASTE_PROFILE));
		coffee.setBalance(parsedData.get(Keyword.BALANCE));
		coffee.setOriginCountry(country);
		coffee.setOriginRegion(parsedData.get(Keyword.REGION));
		coffee.setVariety(parsedData.get(Keyword.VARIETY));
		coffee.setProcessing(parsedData.get(Keyword.PROCESSING));
		coffee.setYear(Integer.valueOf(parsedData.get(Keyword.YEAR)));
//		coffee.setPacks();
//		coffee.setRoastType();
//		coffee.setScaRate();
//		coffee.setImage();
		return coffee;
	}
}
