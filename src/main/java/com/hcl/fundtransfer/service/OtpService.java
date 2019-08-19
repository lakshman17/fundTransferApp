package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.OtpResponseDto;

public interface OtpService {
	
	OtpResponseDto getOtp();
	
	//OtpResponseDto getOtpRestTemplate();

}
