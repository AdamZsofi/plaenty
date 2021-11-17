package io.swagger.api.controller;

import io.swagger.api.interfaces.DashboardApi;
import io.swagger.model.generated.SystemState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class DashboardController implements DashboardApi {
	@Override
	@GetMapping
	public ResponseEntity<SystemState> sendDashboardData() {
		return null;
	}
}
