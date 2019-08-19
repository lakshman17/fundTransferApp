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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.dto.TransactionDto;
import com.hcl.fundtransfer.service.TransactionService;
import com.hcl.fundtransfer.service.TransactionServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class TransactionController {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	TransactionService transactionService;

	@PostMapping("/fundtransfer")
	public ResponseEntity<ApplicationResponse> fundTransfer(@RequestBody FundtransferDto fundTransferDto) {
		LOGGER.info("fundtransfer controller");
		return new ResponseEntity<>(transactionService.doFundTransfer(fundTransferDto), HttpStatus.CREATED);
	}

	@GetMapping("/viewTransactions/{accountNumber}")
	public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable String accountNumber) {
		LOGGER.info("view last ten transactions controller");
		return new ResponseEntity<>(transactionService.getTransacions(accountNumber), HttpStatus.OK);
	}

}
