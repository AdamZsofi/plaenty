package io.swagger.api.controller;

import io.swagger.api.interfaces.ActiveConfigurationApi;
import io.swagger.model.generated.Configuration;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/active-configuration")
public class ActiveConfigurationController implements ActiveConfigurationApi {
	// todo @Autowired repository

	@Override
	@GetMapping
	public ResponseEntity<Configuration> activeConfiguration() {
		return null;
	}

	@Override
	// todo: how does oauth sec work?
	@SecurityRequirement(name = "oAuthSecurity")
	@PutMapping("{id}")
	public ResponseEntity<Configuration> activeConfigurationIdPut(@PathVariable Integer id) {
		return null;
	}
}
