package io.swagger.model;

import nonapi.io.github.classgraph.json.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Configuration {
	@javax.persistence.Id
	@Id
	@GeneratedValue
	private long id;
	private double name;
	private double ecmin;
	private double ecmax;
	private double phmin;
	private double phmax;
	private LightLevel lightRequired;
	private int pumpon;
	private int pumpoff;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
