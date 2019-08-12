package com.hcl.fundtransfer.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.fundtransfer.dto.CustomerDTO;
import com.hcl.fundtransfer.dto.CustomerResponseDTO;
import com.hcl.fundtransfer.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	CustomerService customerService;
	
	CustomerDTO customerDTO;
	CustomerResponseDTO customerResponseDTO;
	
	@Before
	public void setup() {
		customerDTO=new CustomerDTO();
		customerResponseDTO=new CustomerResponseDTO();
		customerDTO.setMobileNumber(9876543210L);
		customerResponseDTO.setMessage("success");
		
	}
	
	@Test
	public void createCustomerTest() throws Exception {

		Mockito.when(customerService.createCustomer(customerDTO)).thenReturn(customerResponseDTO);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/registration").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerResponseDTO))).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
