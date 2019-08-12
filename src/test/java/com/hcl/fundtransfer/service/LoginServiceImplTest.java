package com.hcl.fundtransfer.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.dto.LoginDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.exception.UserNotFoundException;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.serviceimpl.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

	@InjectMocks
	LoginServiceImpl loginServiceImpl;
	
	@Mock
	ICustomerRepository iCustomerRepository;
	
	public Customer getCustomer()
	{
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("Gurpreet");
		customer.setLastName("Singh");
		customer.setMobileNumber(9876655876L);
		customer.setPassword("12345");
		return customer;
	}
	
	/*
	 * public LoginDto getLoginDto() {
	 * 
	 * LoginDto loginDto = new LoginDto(); loginDto.setCustomerId(1L);
	 * loginDto.setPassword("12345"); return loginDto; }
	 */
	
	public CustomerLoginDto getCustomerLoginDto()
	{
		CustomerLoginDto customer = new CustomerLoginDto();
		customer.setAccountNumber(123456L);
		customer.setPassword("12345");
		return customer;
	}
	@Test
	public void testLoginCustomer()
	{
		Customer customer = getCustomer();
		CustomerLoginDto loginDto = getCustomerLoginDto();
		Mockito.when(iCustomerRepository.findByAccountNumberAndPassword(loginDto.getAccountNumber(),loginDto.getPassword())).thenReturn(customer);
		assertEquals("login successfull", loginServiceImpl.loginCustomer(loginDto));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testLoginCustomerNegative()
	{
		CustomerLoginDto loginDto;
		Customer customer = getCustomer();
		loginDto = getCustomerLoginDto();
//		try {
			Mockito.when(iCustomerRepository.findByAccountNumberAndPassword(null, null)).thenReturn(customer);
//			fail("Customer not found", iCustomerRepository.findByAccountNumberAndPassword(null, null));
//		} catch (Exception e) {
			assertEquals(new UserNotFoundException("Customer not found"), loginServiceImpl.loginCustomer(loginDto));
//		}
	}
}
