package com.hcl.fundtransfer.service;

import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerLoginDto;

@Service
public interface ILoginService {
	/**
	 * This api is intended to login the customer
	 */
	public String loginCustomer(CustomerLoginDto loginDTO);

}
