package hu.bme.aut.plaenty.model;

import lombok.Data;

public @Data class Configuration {
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
