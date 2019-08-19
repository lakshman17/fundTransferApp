package com.hcl.fundtransfer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;
import com.hcl.fundtransfer.dto.PayeeUpdateRequestDto;
import com.hcl.fundtransfer.service.PayeeService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class PayeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayeeController.class);

	@Autowired
	PayeeService payeeService;

	@PostMapping("/addPayee")
	public ResponseEntity<PayeeResponseDto> createPayee(@RequestBody PayeeRequestDto request) {
		PayeeResponseDto response = payeeService.createPayee(request);
		LOGGER.info("adding payee");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/payees/{customerId}")
	public ResponseEntity<List<PayeeRequestDto>> getAllPayees(@PathVariable Integer customerId) {
		LOGGER.info("fetching payees");
		List<PayeeRequestDto> payeeList = payeeService.getAllPayees(customerId);
		return new ResponseEntity<>(payeeList, HttpStatus.OK);
	}

	@PutMapping("/updatePayee/{payeeId}")
	public ResponseEntity<PayeeResponseDto> updatePayee(@PathVariable Integer payeeId,
			@RequestBody PayeeUpdateRequestDto request) {
		PayeeResponseDto response = payeeService.updatePayee(payeeId, request);
		LOGGER.info("updating payee");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deletePayee/{payeeId}/{customerId}")
	public ResponseEntity<PayeeResponseDto> deletePayee(@PathVariable Integer payeeId,
			@PathVariable Integer customerId) {
		PayeeResponseDto response = payeeService.deletePayee(payeeId, customerId);
		LOGGER.info("deleting payee");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
