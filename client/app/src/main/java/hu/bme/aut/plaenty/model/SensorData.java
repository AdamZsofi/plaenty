package hu.bme.aut.plaenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * A single measurement taken by a given sensor
 */
@AllArgsConstructor
public @Data class SensorData {
	private Long measurementId = null;
	private double value;
	private String time;
	private long sensorId;
}
