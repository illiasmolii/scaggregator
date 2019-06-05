package ua.smolii.scaggregator.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.util.List;

import lombok.Getter;

public enum Keyword {

	NAME(emptyList()),
	DESCRIPTION(emptyList()),
	ROAST_TYPE(singletonList("Обсмажка")),
	TASTE_PROFILE(singletonList("Смаковий профіль")),
	BALANCE(singletonList("Баланс смаку")),
	PROCESSING(singletonList("Обробка")),
	VARIETY(singletonList("Різновид")),
	COUNTRY(singletonList("Регіон")),
	REGION(singletonList("Регіон")),
	YEAR(singletonList("Рік врожаю")),
	SCA_RATE(singletonList("Дегустаційний бал"));

	@Getter
	private List<String> words;

	Keyword(List<String> words) {
		this.words = words;
	}
}
