package hu.bme.aut.plaentyapp.model.configuration;

import hu.bme.aut.plaentyapp.model.io.sensor.LightLevel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public @Data class Configuration {
	@Id
	@GeneratedValue
	private Long id = null;
	private String author;
	private String name;
	private double ecmin;
	private double ecmax;
	private double phmin;
	private double phmax;
	private LightLevel lightRequired;
	private int pumpon;
	private int pumpoff;
}
