package com.hcl.fundtransfer.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.dto.OtpResponseDto;
import com.hcl.fundtransfer.service.OtpService;
import com.hcl.fundtransfer.util.EmailSender;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class OtpController {
	public static final Logger LOGGER = LoggerFactory.getLogger(OtpController.class);
	@Autowired
	OtpService otpService;
	
	@Autowired
	EmailSender emailSender;

//	@Autowired
//	RestTemplate restTemplate;

	@GetMapping("/otp")
	public ResponseEntity<OtpResponseDto> getAccountSummary() {
		LOGGER.info("Otp  controller");
		return new ResponseEntity<>(otpService.getOtp(), HttpStatus.OK);
	}
	
	@GetMapping("/sendotp")
	public ResponseEntity<String> sendOtp() throws Exception {
		LOGGER.info("send otp gmail");
		return new ResponseEntity<>(emailSender.sendOtp(), HttpStatus.OK);
	}

//	@GetMapping("/rest/otp")
//	public ResponseEntity<OtpResponseDto> getOtpRestTemplate() {
//		LOGGER.info("enter in to otp resttemplate service impl");
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//		return new ResponseEntity<>(restTemplate
//				.exchange("http://localhost:9090/fundtrasfer/api/otp", HttpMethod.GET, entity, OtpResponseDto.class).getBody(),
//				HttpStatus.OK);
//	}

}
