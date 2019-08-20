package com.hcl.fundtransfer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.StatementResponseDto;
import com.hcl.fundtransfer.service.StatementService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class StatementController {
	public static final Logger LOGGER = LoggerFactory.getLogger(StatementController.class);

	@Autowired
	StatementService statementService;

	@GetMapping("/statements/{cardId}/{purchaseDate}")
	public ResponseEntity<List<StatementResponseDto>> getAllStatements(@PathVariable Integer cardId,@PathVariable String purchaseDate ) {
		LOGGER.info("Monthly statements listing");
		List<StatementResponseDto> response = statementService.getAllStatements(cardId,purchaseDate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
