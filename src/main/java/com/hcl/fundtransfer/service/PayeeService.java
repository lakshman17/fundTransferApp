package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;

public interface PayeeService {

	PayeeResponseDto createPayee(PayeeRequestDto request);
	
	String sendEmail();

}
