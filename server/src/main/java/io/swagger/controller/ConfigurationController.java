package io.swagger.controller;

import io.swagger.model.configuration.Configuration;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
	@GetMapping("{id}")
	public ResponseEntity<Configuration> configurationIdGet(@PathVariable Integer id) {
		return null;
	}

	@PutMapping("{id}")
	@SecurityRequirement(name = "oAuthSecurity")
	public ResponseEntity<Configuration> configurationIdPut(@PathVariable Integer id) {
		return null;
	}

	@GetMapping("/list")
	public ResponseEntity<List<Configuration>> configurationListGet() {
		return null;
	}

	@PostMapping
	@SecurityRequirement(name = "oAuthSecurity")
	public ResponseEntity<Void> configurationPost(@Valid @RequestBody Configuration body) {
		return null;
	}
}
