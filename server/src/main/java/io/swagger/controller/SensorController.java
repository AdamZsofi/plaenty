package io.swagger.controller;

import io.swagger.model.system.HydroponicSystem;
import io.swagger.model.system.sensor.Sensor;
import io.swagger.model.system.sensor.SensorContainer;
import io.swagger.model.system.sensor.SensorData;
import io.swagger.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {
	@Autowired
	SensorDataRepository sensorDataRepository;

	@Autowired
	SensorContainer sensors;

	@GetMapping("/data/{sensorid}")
	public ResponseEntity<List<SensorData>> sendSensorData(@PathVariable Integer sensorid, @Valid @RequestParam(value = "from", required = false) LocalDateTime from) {
		List<SensorData> sensorData;
		if(from!=null) {
			sensorData = sensorDataRepository.getSensorData(sensors.getSensorById(sensorid), from);
		} else {
			sensorData = sensorDataRepository.getSensorData(sensors.getSensorById(sensorid));
		}
		return ResponseEntity.ok(sensorData);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Sensor>> sendSensorList() {
		return ResponseEntity.ok(sensors.getSensorList());
	}
}
