package ua.smolii.scaggregator.domain;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public enum Country {

	ETHIOPIA(emptyList()),
	KENYA(emptyList()),
	RWANDA(emptyList()),
	BURUNDI(emptyList()),
	TANZANIA(emptyList()),
	CONGO(emptyList()),
	DR_CONGO(emptyList()),
	UGANDA(emptyList()),
	MALAWI(emptyList()),
	CAMEROON(emptyList()),
	ZAMBIA(emptyList()),

	EL_SALVADOR(singletonList("El Salvador")),
	GUATEMALA(emptyList()),
	HONDURAS(emptyList()),
	NICARAGUA(emptyList()),
	COSTA_RICA(emptyList()),
	PANAMA(emptyList()),
	BELIZE(emptyList()),
	JAMAICA(emptyList()),
	CUBA(emptyList()),
	MEXICO(emptyList()),
	DOMINICAN_REPUBLIC(emptyList()),
	HAWAII(emptyList()),

	COLOMBIA(emptyList()),
	ECUADOR(emptyList()),
	PERU(emptyList()),
	BRAZIL(emptyList()),
	BOLOVIA(emptyList()),
	VENEZUELA(emptyList()),

	YEMEN(emptyList()),
	INDIA(emptyList()),
	INDONESIA(emptyList()),
	PAPUA_NEW_GUINEA(emptyList());

	@Getter
	private List<String> wordings;

	Country(List<String> wordings) {
		this.wordings = wordings;
	}

	public static Country fromWording(String wording) {
		return Arrays.stream(Country.values())
				.filter(country -> country.getWordings().contains(wording))
				.findFirst()
				.orElse(null);
	}
}
