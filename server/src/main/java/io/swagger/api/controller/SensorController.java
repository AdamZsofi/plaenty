package io.swagger.api.controller;

import io.swagger.api.interfaces.SensorApi;
import io.swagger.model.generated.Sensordatalist;
import io.swagger.model.generated.Sensorlist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;

public class SensorController implements SensorApi {
	@Override
	@GetMapping
	public ResponseEntity<Sensordatalist> sendSensorData(@PathVariable Integer sensorid, @Valid @RequestParam(value = "from", required = false) OffsetDateTime from) {
		return null;
	}

	@Override
	@GetMapping
	public ResponseEntity<Sensorlist> sendSensorList() {
		return null;
	}
}
