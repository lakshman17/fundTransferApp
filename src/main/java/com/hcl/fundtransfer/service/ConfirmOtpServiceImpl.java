package com.hcl.fundtransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.util.EmailSender;

@Service
public class ConfirmOtpServiceImpl implements ConfirmOtpService {

	@Autowired
	private EmailSender emailSender;

	@Override
	public boolean sendOtp() {
//		try {
//			emailSender.sendOtp(request);
//			return true;
//		} catch (Exception e) {
//			System.out.println("send otp failed :" + e.getMessage());
//		}
		return false;

	}

}
