package com.hcl.fundtransfer.controller;

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

import com.hcl.fundtransfer.dto.AccountSummaryReponse;
import com.hcl.fundtransfer.service.AccountSummaryService;
import com.hcl.fundtransfer.service.TransactionServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class AccountSummaryController {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	AccountSummaryService accuntSummaryService;

	@GetMapping("/viewAccountSummary/{accountNumber}")
	public ResponseEntity<AccountSummaryReponse> getAccountSummary(@PathVariable String accountNumber) {
		LOGGER.info("AccountSummary controller controller");
		return new ResponseEntity<>(accuntSummaryService.getAccountSummary(accountNumber), HttpStatus.OK);
	}

}
