package com.accenture.javacapability.backbone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.accenture.javacapability.backbone.service.interfaces.ICustomerService;
import com.accenture.javacapability.model.Customer;

@RestController("/")
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("country/{id}")
	public ResponseEntity<?> getCountryFromMsLocalization(@PathVariable("id")Long id){
		
		String url = "http://ms-localization/countryMS/"+id;
		
		String countryName = restTemplate.getForObject(url, String.class);
			
		return new ResponseEntity<>(countryName,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("generateMockData")
	public ResponseEntity<?> generateMockData() {
		return new ResponseEntity<>(customerService.generateMockData(),HttpStatus.OK);
	}
	
	@GetMapping("customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id")Long id) {
		Customer customer = customerService.getCustomer(id);
		
		if(customer != null)
			return new ResponseEntity<>(customer,HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	
	
	
}
