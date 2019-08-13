package com.hcl.fundtransfer.service;

import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.dto.LoginResponseDto;

@Service
public interface ILoginService {
	/**
	 * This api is intended to login the customer
	 */
	public LoginResponseDto loginCustomer(CustomerLoginDto loginDTO);
}
