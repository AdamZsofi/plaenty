package io.swagger.model.system.sensor;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

/**
 * A single measurement taken by a given sensor
 */
public @Data
class SensorData {
	@javax.persistence.Id
	@Id
	@GeneratedValue
	private long measurementId;
	private double value;
	private LocalDateTime time;
	private long sensorId;
}
