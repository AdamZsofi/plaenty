package io.swagger.model.system;

import io.swagger.model.configuration.Configuration;
import io.swagger.model.system.sensor.Sensor;
import io.swagger.model.system.sensor.SensorData;
import lombok.Data;

import java.util.HashMap;

@Data class SystemState {
	private Configuration activeConfiguration;
	private HashMap<Sensor, SensorData> sensorState;
}