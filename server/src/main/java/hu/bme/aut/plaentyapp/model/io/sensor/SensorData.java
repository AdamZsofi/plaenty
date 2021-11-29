package hu.bme.aut.plaentyapp.model.io.sensor;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

/**
 * A single measurement taken by a given sensor
 */
@Entity
public @Data class SensorData {
	@javax.persistence.Id
	@Id
	@GeneratedValue
	private Long measurementId = null;
	private double value;
	private LocalDateTime time;
	private long sensorId;
}
