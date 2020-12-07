package com.su.springboot.app.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.su.springboot.app.booking.models.service.MetricService;

@RestController
public class MetricController {

	@Autowired
	private MetricService metricService;

	@GetMapping("/metrics")
	public ResponseEntity<?> findAllMetrics() {     
		return ResponseEntity.ok(metricService.findAll());
	}
}
