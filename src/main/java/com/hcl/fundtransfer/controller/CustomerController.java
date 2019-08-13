package com.hcl.fundtransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.CustomerDTO;
import com.hcl.fundtransfer.dto.CustomerResponseDTO;
import com.hcl.fundtransfer.service.CustomerService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/registration")
	public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		LOGGER.info("inside registration");
		CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customerDTO);
		return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
	}

}
