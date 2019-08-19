package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.ConfirmPayeeRequestDto;

public interface ConfirmOtpService {

//	boolean sendOtp();

	ApplicationResponse confirmPayee(ConfirmPayeeRequestDto confirmPayeeRequestDto);
}
