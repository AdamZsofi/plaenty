package io.swagger.controller;

import io.swagger.model.configuration.Configuration;
import io.swagger.model.system.HydroponicSystem;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
	HydroponicSystem system;

	@GetMapping
	public ResponseEntity<Configuration> activeConfiguration() {
		Configuration activeConfiguration = system.getActiveConfiguration();
		if(activeConfiguration==null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(activeConfiguration);
		}
	}

	// TODO how does oauth sec work?
	@SecurityRequirement(name = "oAuthSecurity")
	@PutMapping("{id}")
	public ResponseEntity<Configuration> activeConfigurationIdPut(@PathVariable Integer id) {
		try {
			system.updateActiveConfiguration(id);
		} catch (RuntimeException) {
			ResponseEntity.
		}
	}
}
