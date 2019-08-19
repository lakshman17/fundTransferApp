package com.hcl.fundtransfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.dto.LoginResponseDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.exception.UserNotFoundException;
import com.hcl.fundtransfer.repository.IAccountRepository;
import com.hcl.fundtransfer.repository.ICustomerRepository;

@Service
public class LoginServiceImpl implements ILoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	ICustomerRepository iCustomerRepository;

	@Autowired
	IAccountRepository iAccountRepository;

	/**
	 * @author Gurpreet Singh This method is intended to login the customer by
	 *         taking account number and password for login
	 * @param loginDto is the input request which includes accountNumber and
	 *                 password
	 * @return it returns "login successful" message
	 */
	@Override
	public LoginResponseDto loginCustomer(CustomerLoginDto loginDTO) {
		Customer customer = iCustomerRepository.findByAccountNumberAndPassword(loginDTO.getAccountNumber(),
				loginDTO.getPassword());
		LOGGER.info("inside login");
		if (customer != null) {
			return new LoginResponseDto("login successfull",customer.getCustomerId(), customer.getAccountNumber());
		} else {
			throw new UserNotFoundException("Customer not found");
		}
	}

}
