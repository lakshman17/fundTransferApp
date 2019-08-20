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

import com.hcl.fundtransfer.dto.CardValidationRequestDto;
import com.hcl.fundtransfer.dto.CardValidationResponseDto;
import com.hcl.fundtransfer.service.CardValidationService;
import com.hcl.fundtransfer.util.EmailSender;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class CardValidationController {
	public static final Logger LOGGER = LoggerFactory.getLogger(CardValidationController.class);

	@Autowired
	CardValidationService cardValidationService;

	@Autowired
	EmailSender emailSender;

	@PostMapping("/cardValidation")
	public ResponseEntity<CardValidationResponseDto> getCardValidation(
			@RequestBody CardValidationRequestDto cardValidationRequestDto) {
		LOGGER.info("CardValidation  controller");
		return new ResponseEntity<>(cardValidationService.getCardValidationDetails(cardValidationRequestDto),
				HttpStatus.OK);
	}

}
