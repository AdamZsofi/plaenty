package io.swagger.controller;

import io.swagger.model.system.HydroponicSystem;
import io.swagger.model.system.SystemState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	HydroponicSystem system;

	@GetMapping
	public ResponseEntity<SystemState> sendDashboardData() {
		return ResponseEntity.ok(system.getSystemState());
	}
}
