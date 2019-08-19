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

import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;
import com.hcl.fundtransfer.service.PayeeService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class PayeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayeeController.class);
	
	@Autowired
	PayeeService payeeService;
	
	@PostMapping("/addPayee")
	public ResponseEntity<PayeeResponseDto> createPayee(@RequestBody PayeeRequestDto request){
		PayeeResponseDto response = payeeService.createPayee(request);
		LOGGER.info("adding payee");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
