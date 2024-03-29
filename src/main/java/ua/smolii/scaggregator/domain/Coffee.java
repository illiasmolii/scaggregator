package ua.smolii.scaggregator.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "COFFEE")
@Data
public class Coffee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ROASTER")
	private String roaster;

	@Column(name = "TASTE_PROFILE")
	private String tasteProfile;

	@Column(name = "BALANCE")
	private String balance;

	@Column(name = "ORIGIN_COUNTRY")
	@Enumerated(EnumType.STRING)
	private Country originCountry;

	@Column(name = "ORIGIN_REGION")
	private String originRegion;

	@Column(name = "VARIETY")
	private String variety;

	@Column(name = "PROCESSING")
	private String processing;

	@Column(name = "YEAR")
	private Integer year;

	@ElementCollection
	@CollectionTable(name = "PACK", joinColumns = @JoinColumn(name = "COFFEE_ID"))
	private List<Pack> packs;

	@Column(name = "ROAST_TYPE")
	@Enumerated(EnumType.STRING)
	private RoastType roastType;

	@Column(name = "SCA_RATE")
	private BigDecimal scaRate;

	@Column(name = "IMAGE")
	private String image;

}
