package io.swagger.controller;

import io.swagger.configuration.ConfigurationNotFoundException;
import io.swagger.model.configuration.Configuration;
import io.swagger.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
	@Autowired
	ConfigurationRepository configurationRepository;

	@GetMapping("{id}")
	public ResponseEntity<Configuration> configurationIdGet(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(configurationRepository.getConfiguration(id));
		} catch (ConfigurationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	// TODO add oauth
	public ResponseEntity<Configuration> configurationIdPut(@Valid @RequestBody Configuration body) {
		try {
			return ResponseEntity.ok(configurationRepository.updateConfiguration(body));
		} catch (ConfigurationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<Configuration>> configurationListGet() {
		return ResponseEntity.ok(configurationRepository.getConfigurationList());
	}

	@PostMapping
	// TODO add oauth
	public ResponseEntity<Configuration> configurationPost(@Valid @RequestBody Configuration body) {
		return ResponseEntity.ok(configurationRepository.saveConfiguration(body));
	}
}
