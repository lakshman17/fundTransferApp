package com.hcl.fundtransfer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.fundtransfer.dto.CustomerLoginDto;
import com.hcl.fundtransfer.service.ILoginService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ILoginService iLoginService;


	@Test
	public void testLoginCustomer() throws Exception {

		CustomerLoginDto loginDTO = new CustomerLoginDto();
		loginDTO.setAccountNumber("345667");
		loginDTO.setPassword("sample");
		mockMvc.perform(MockMvcRequestBuilders.put("/api/login").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(loginDTO))).andExpect(MockMvcResultMatchers.status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
