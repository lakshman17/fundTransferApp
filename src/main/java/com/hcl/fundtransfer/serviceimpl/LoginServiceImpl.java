package com.hcl.fundtransfer.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.exception.UserNotFoundException;
import com.hcl.fundtransfer.repository.IAccountRepository;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	ICustomerRepository iCustomerRepository;
	
	@Autowired
	IAccountRepository iAccountRepository;
	
	/**
	 * This api is intended to login the customer
	 */
	@Override
	public String loginCustomer(CustomerLoginDto loginDTO) {
		Customer customer = iCustomerRepository.findByAccountNumberAndPassword(loginDTO.getAccountNumber(),loginDTO.getPassword());
		if(customer!=null) {
			return "login successfull";
		}else {
			throw new UserNotFoundException("Customer not found");
		}
	}
	
	

	
}
