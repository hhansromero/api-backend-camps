package com.su.springboot.app.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.su.springboot.app.commons.models.entity.Company;

@RestController
public class MetricGatheringController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/gathering/companies")
	public ResponseEntity<?> findAllCompanies(){	
		return ResponseEntity.ok(restTemplate.getForObject("http://localhost:8001/list", Company[].class));
	}

//	@PostMapping("/create")
//	public ResponseEntity<?> create(@RequestBody Company company){
//		HttpEntity<String> request = new HttpEntity<String>(company.toString());
//		return ResponseEntity.ok(restTemplate.postForObject("http://localhost:8001/create", request, String.class));
//	}


}
