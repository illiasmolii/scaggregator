package ua.smolii.scaggregator.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PACK")
@Data
public class Pack {

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "WEIGHT")
	private Integer weight;
}
