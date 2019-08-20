/**
 * 
 */
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
import com.hcl.fundtransfer.dto.ConfirmOtpRequestDto;
import com.hcl.fundtransfer.dto.ConfirmOtpResponseDto;
import com.hcl.fundtransfer.service.ConfirmCardOtpService;
import com.hcl.fundtransfer.util.EmailSender;

/**
 * @author user1
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ConfirmCardOtpController {
	public static final Logger LOGGER = LoggerFactory.getLogger(ConfirmCardOtpController.class);

	@Autowired
	ConfirmCardOtpService confirmCardOtpService;

	@Autowired
	EmailSender emailSender;

	@PostMapping("/comfirmOtp")
	public ResponseEntity<ConfirmOtpResponseDto> confirmOtp(@RequestBody ConfirmOtpRequestDto confirmOtpRequestDto) {
		LOGGER.info(" credit Otp  controller");
		ConfirmOtpResponseDto response = confirmCardOtpService.confirmOtp(confirmOtpRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
