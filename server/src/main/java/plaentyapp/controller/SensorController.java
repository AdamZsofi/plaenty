package plaentyapp.controller;

import org.springframework.format.annotation.DateTimeFormat;
import plaentyapp.model.system.sensor.Sensor;
import plaentyapp.model.system.sensor.SensorContainer;
import plaentyapp.model.system.sensor.SensorData;
import plaentyapp.repository.SensorDataRepository;
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
	public ResponseEntity<List<SensorData>> sendSensorData(@PathVariable Integer sensorid, @Valid @RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from) {
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