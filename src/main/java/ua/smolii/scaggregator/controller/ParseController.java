package ua.smolii.scaggregator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.smolii.scaggregator.service.Roaster;

@RestController
@AllArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class ParseController {

	private final List<Roaster> roasters;

	@PostMapping("/parse")
	public void parse() {
		log.info("Start parsing");
		roasters.forEach(Roaster::populateCatalog);
	}
}
