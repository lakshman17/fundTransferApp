/**
 * 
 */
package com.hcl.fundtransfer.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.fundtransfer.dto.ConfirmOtpRequestDto;
import com.hcl.fundtransfer.dto.ConfirmOtpResponseDto;
import com.hcl.fundtransfer.service.ConfirmCardOtpService;

/**
 * @author user1
 *
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ConfirmCardOtpControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	ConfirmCardOtpController confirmCardOtpController;
	
	@Mock	
	ConfirmCardOtpService confirmCardOtpService;
	
	
	ConfirmOtpRequestDto confirmOtpRequestDto;
	ConfirmOtpResponseDto confirmOtpResponseDto;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(confirmCardOtpController).build();
		confirmOtpRequestDto = new ConfirmOtpRequestDto();
		confirmOtpRequestDto.setCardId(2);
		
		
		confirmOtpResponseDto=new ConfirmOtpResponseDto();
		confirmOtpResponseDto.setMessage("otp verified successfully");
	
	}
	
	@Test
	public void testConfirmOtp() throws Exception {

		Mockito.when(confirmCardOtpService.confirmOtp(confirmOtpRequestDto)).thenReturn(confirmOtpResponseDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/comfirmOtp").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(confirmOtpResponseDto))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
