package hu.bme.aut.plaentyapp.restapi.controller;

import hu.bme.aut.plaentyapp.model.system.HydroponicSystem;
import org.springframework.format.annotation.DateTimeFormat;
import hu.bme.aut.plaentyapp.model.io.sensor.Sensor;
import hu.bme.aut.plaentyapp.model.io.sensor.SensorData;
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
	HydroponicSystem system;

	@GetMapping("/data/{sensorid}")
	public ResponseEntity<List<SensorData>> sendSensorData(@PathVariable Long sensorid, @Valid @RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from) {
		List<SensorData> sensorData;
		if(from!=null) {
			sensorData = system.getSensorData(sensorid, from);
		} else {
			sensorData = system.getSensorData(sensorid);
		}
		return ResponseEntity.ok(sensorData);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Sensor>> sendSensorList() {
		return ResponseEntity.ok(system.getSensorList());
	}
}
