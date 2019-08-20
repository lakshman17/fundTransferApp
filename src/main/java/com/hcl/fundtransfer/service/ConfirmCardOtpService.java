/**
 * 
 */
package com.hcl.fundtransfer.service;

import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.ConfirmOtpRequestDto;
import com.hcl.fundtransfer.dto.ConfirmOtpResponseDto;

/**
 * @author user1
 *
 */
@Service
public interface ConfirmCardOtpService {

	/**
	 * This method is use to provide
	 */
	public ConfirmOtpResponseDto confirmOtp(ConfirmOtpRequestDto confirmOtpRequestDto);

}
