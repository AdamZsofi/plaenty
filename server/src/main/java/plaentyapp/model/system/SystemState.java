package plaentyapp.model.system;

import plaentyapp.model.configuration.Configuration;
import plaentyapp.model.system.sensor.Sensor;
import plaentyapp.model.system.sensor.SensorData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
public @Data final class SystemState {
	private final Configuration activeConfiguration;
	private final HashMap<Long, SensorData> sensorState;
	boolean isPumpOn;
	boolean isGrowlightOn;
}