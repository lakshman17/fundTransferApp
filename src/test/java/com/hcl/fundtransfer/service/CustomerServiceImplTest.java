package com.hcl.fundtransfer.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.fundtransfer.DTO.CustomerDTO;
import com.hcl.fundtransfer.DTO.CustomerResponseDTO;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.repository.AccountRepository;
import com.hcl.fundtransfer.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	@Mock
	CustomerRepository customerRepository;
	@Mock
	AccountRepository accountRepository;

	CustomerResponseDTO customerResponseDTO;
	CustomerDTO customerDTO;
	Customer customer;
	Account account;
	
	
	@Before
	public void setup() {
		customerDTO = new CustomerDTO();
		customerDTO.setFirstName("deepi");
		
		customer = new Customer();
		customer.setFirstName("deepi");
		
		customerResponseDTO=new CustomerResponseDTO();
		customerResponseDTO.setMessage("Registration Successfull");
	
		account = new Account();
		account.setAccountId(1L);
	}
	
	@Test
	public void createCustomerTest() {
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);
		customerResponseDTO= customerServiceImpl.createCustomer(customerDTO);
		assertEquals("Registration Successfull", customerResponseDTO.getMessage());
		
	}
	
}
