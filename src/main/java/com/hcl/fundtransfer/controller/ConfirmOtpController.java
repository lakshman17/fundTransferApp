package com.hcl.fundtransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.ConfirmPayeeRequestDto;
import com.hcl.fundtransfer.service.ConfirmOtpService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class ConfirmOtpController {
	public static final Logger LOGGER = LoggerFactory.getLogger(ConfirmOtpController.class);
	@Autowired
	ConfirmOtpService confirmOtpService;

	@PutMapping("/confirmOtp")
	public ResponseEntity<ApplicationResponse> confirmOtp(@RequestBody ConfirmPayeeRequestDto confirmPayeeRequestDto) {
		LOGGER.info("Otp  controller");
		return new ResponseEntity<>(confirmOtpService.confirmPayee(confirmPayeeRequestDto), HttpStatus.CREATED);
	}

}
