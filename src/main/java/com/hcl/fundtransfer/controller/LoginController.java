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

import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.service.ILoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = {"*","*/"},origins= {"*","*/"})
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	ILoginService iLoginService;
		
	@PutMapping("/login")
	public ResponseEntity<String> loginCustomer(@RequestBody CustomerLoginDto loginDTO) 
	{
		logger.info("in login customer method");
		String response = iLoginService.loginCustomer(loginDTO);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
