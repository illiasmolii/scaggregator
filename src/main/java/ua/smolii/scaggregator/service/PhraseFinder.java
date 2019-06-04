package ua.smolii.scaggregator.service;

import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class PhraseFinder {

	public Optional<Pair<Keyword, String>> findKeyword(String text) {
		for (Keyword keyword: Keyword.values()) {
			String foundKeyword = keyword.getWords().stream()
					.filter(keywordText -> text.contains(keywordText))
					.findFirst()
					.orElse(null);
			if (foundKeyword != null) {
				return Optional.of(Pair.of(keyword, foundKeyword));
			}
		}
		return Optional.empty();
	}
}
