package com.hcl.fundtransfer.service;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerDTO;
import com.hcl.fundtransfer.dto.CustomerResponseDTO;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.repository.IAccountRepository;
import com.hcl.fundtransfer.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	ICustomerRepository customerRepository;
	@Autowired
	IAccountRepository accountRepository;
	/**
	 * @author Deepika S
	 * This method is used for customer registration 
	 * @param customerDTO it is the request object 
	 * which contains firstName,lastName,phoneNumber and password
	 * @return it returns accountNumber and message("Registration successful")
	 * 
	 */
	
	@Override
	public CustomerResponseDTO createCustomer(CustomerDTO customerDTO) {

		Customer customer = new Customer();
		Account account = new Account();

		String accno = "ING"+customerDTO.getLastName()+accountNumber();

		customerDTO.setAccountNumber(accno);
		BeanUtils.copyProperties(customerDTO, customer);
		customerRepository.save(customer);
		LOGGER.info("customer registered");

		account.setAccountNumber(accno);
		account.setBalance(10000.00);
		account.setCustomer(customer);
		accountRepository.save(account);
		LOGGER.info("account saved");

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setAccountNumber(accno);
		customerResponseDTO.setMessage("Registration Successful");
		LOGGER.info("Account number generated");
		return customerResponseDTO;
	}

	private Long accountNumber() {
		 return ThreadLocalRandom.current().nextLong(10000000, 90000000);
	}

}
