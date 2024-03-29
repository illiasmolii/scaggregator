package ua.smolii.scaggregator.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@AllArgsConstructor
public class Pack {

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "WEIGHT")
	private Integer weight;
}
