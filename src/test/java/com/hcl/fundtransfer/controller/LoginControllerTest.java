package com.hcl.fundtransfer.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.service.ILoginService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class LoginControllerTest {
private MockMvc mockMvc;
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	ILoginService iLoginService;
	
	Customer customer;
	CustomerLoginDto loginDto;
	
	@Before
	public void init()
	{
		customer = new Customer(1L, 237654L, "Gurpreet", "Singh", "12345", 9876547698L);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}
	
	@Test
	public void loginCustomer() throws Exception
	{
		loginDto = new CustomerLoginDto();
//		Mockito.when(iLoginService.loginCustomer(loginDto)).thenReturn("login successfull");
//		mockMvc.perform(MockMvcRequestBuilders.put("/login").contentType(MediaType.APPLICATION_JSON).
//				accept(MediaType.ALL).content(asJsonString(customer)));
		 
		loginDto.setAccountNumber(456789L);
		loginDto.setPassword("12345");
		Mockito.when(iLoginService.loginCustomer(Mockito.any())).thenReturn("login successfull");
		mockMvc.perform(MockMvcRequestBuilders.put("/login")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDto)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
