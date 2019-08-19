package com.hcl.fundtransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.OtpResponseDto;
import com.hcl.fundtransfer.service.OtpService;
import com.hcl.fundtransfer.service.PayeeService;
import com.hcl.fundtransfer.util.EmailSender;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class OtpController {
	public static final Logger LOGGER = LoggerFactory.getLogger(OtpController.class);
	@Autowired
	OtpService otpService;

	@Autowired
	PayeeService payeeService;

	@Autowired
	EmailSender emailSender;

	@GetMapping("/otp")
	public ResponseEntity<OtpResponseDto> getAccountSummary() {
		LOGGER.info("Otp  controller");
		return new ResponseEntity<>(otpService.getOtp(), HttpStatus.OK);
	}

}
