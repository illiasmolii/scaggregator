package ua.smolii.scaggregator.domain;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public enum RoastType {

	ESPRESSO(emptyList()),
	FILTER(singletonList("Фільтр")),
	OMNI(emptyList());

	@Getter
	private List<String> wordings;

	RoastType(List<String> wordings) {
		this.wordings = wordings;
	}

	public static RoastType fromWording(String wording) {
		return Arrays.stream(RoastType.values())
				.filter(type -> type.getWordings().contains(wording))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown roast type: " + wording));
	}
}
