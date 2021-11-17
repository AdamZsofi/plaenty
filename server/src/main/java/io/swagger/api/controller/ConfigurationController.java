package io.swagger.api.controller;

import io.swagger.api.interfaces.ConfigurationApi;
import io.swagger.model.generated.Configuration;
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

@RestController
@RequestMapping("/configuration")
public class ConfigurationController implements ConfigurationApi {
	@Override
	@GetMapping("{id}")
	public ResponseEntity<Configuration> configurationIdGet(@PathVariable Integer id) {
		return null;
	}

	@Override
	@PutMapping("{id}")
	@SecurityRequirement(name = "oAuthSecurity")
	public ResponseEntity<Configuration> configurationIdPut(@PathVariable Integer id) {
		return null;
	}

	@Override
	@GetMapping
	public ResponseEntity<Configuration> configurationListGet() {
		return null;
	}

	@Override
	@PostMapping
	@SecurityRequirement(name = "oAuthSecurity")
	public ResponseEntity<Void> configurationPost(@Valid @RequestBody Configuration body) {
		return null;
	}
}
