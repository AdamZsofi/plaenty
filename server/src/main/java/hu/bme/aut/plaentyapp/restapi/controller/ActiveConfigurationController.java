package hu.bme.aut.plaentyapp.restapi.controller;

import hu.bme.aut.plaentyapp.model.system.HydroponicSystem;
import hu.bme.aut.plaentyapp.model.configuration.ConfigurationNotFoundException;
import hu.bme.aut.plaentyapp.model.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/active-configuration")
public class ActiveConfigurationController {
	@Autowired
	HydroponicSystem hydroponicSystem;

	@GetMapping
	public ResponseEntity<Configuration> activeConfiguration() {
		Configuration activeConfiguration = hydroponicSystem.getActiveConfiguration();
		if(activeConfiguration==null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(activeConfiguration);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Configuration> activeConfigurationIdPut(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(hydroponicSystem.updateActiveConfiguration(id));
		} catch (ConfigurationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
