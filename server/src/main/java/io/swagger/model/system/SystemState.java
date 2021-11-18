package io.swagger.model.system;

import io.swagger.model.configuration.Configuration;
import io.swagger.model.system.sensor.Sensor;
import io.swagger.model.system.sensor.SensorData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data final class SystemState {
	private final Configuration activeConfiguration;
	private final HashMap<Sensor, SensorData> sensorState;
}