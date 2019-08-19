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

	@Override
	public OtpResponseDto getOtp() {
		LOGGER.info("enter in to otp service impl");

		Long otpLong = ThreadLocalRandom.current().nextLong(1000, 9000);

		return new OtpResponseDto(otpLong);
	}

}
