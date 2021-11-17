package io.swagger.model;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public @Data class Sensor {
	@javax.persistence.Id
	@Id
	@GeneratedValue
	private long sensorId;
	private String name;
	private String dimension;
}
