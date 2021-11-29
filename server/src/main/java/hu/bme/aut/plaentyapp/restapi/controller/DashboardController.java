package hu.bme.aut.plaentyapp.restapi.controller;

import hu.bme.aut.plaentyapp.model.system.HydroponicSystem;
import hu.bme.aut.plaentyapp.model.system.SystemState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	HydroponicSystem hydroponicSystem;

	@GetMapping
	public ResponseEntity<SystemState> sendDashboardData() {
		return ResponseEntity.ok(hydroponicSystem.getSystemState());
	}
}
