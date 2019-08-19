package com.hcl.fundtransfer.service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.dto.OtpResponseDto;

@Service
public class OtpServiceImpl implements OtpService {
	public static final Logger LOGGER = LoggerFactory.getLogger(OtpServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;

	Random random = new Random();
	String numbers = "0123456789";
	int otpLength = 4;

	@Override
	public OtpResponseDto getOtp() {
		LOGGER.info("enter in to otp service impl");
//		char[] otp = new char[otpLength];
//
//		StringBuilder sb=new StringBuilder(); 
//		
//		for (int i = 0; i < otpLength; i++) {
//			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
//			sb.append(Arrays.toString(otp));
//		}
//		
//		Long otpLong= Long.valueOf(sb.toString());
	
		
		Long otpLong=ThreadLocalRandom.current().nextLong(1000, 9000);
		
		return new OtpResponseDto(otpLong);
	}

}
