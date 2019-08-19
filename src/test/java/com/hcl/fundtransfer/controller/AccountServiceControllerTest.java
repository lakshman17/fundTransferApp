package com.hcl.fundtransfer.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.service.AccountSummaryService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration

public class AccountServiceControllerTest {

	@Mock
	AccountSummaryService accountSummaryService;

	@InjectMocks
	AccountSummaryController accountSummaryController;

	MockMvc mockMvc;
	

	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(accountSummaryController).build();
	}

	
	@Test
	public void getAccountSummary() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/viewAccountSummary/{accountNumber}",1234L).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public FundtransferDto getFundTransferDto() {
		FundtransferDto fundTransferDto = new FundtransferDto();
		fundTransferDto.setAmount(10000.0);
		fundTransferDto.setFromAccountNumber("5678");
		fundTransferDto.setToAccountNumber("1234");
		
		return fundTransferDto;
	}

	public ApplicationResponse getApplicationResponse()
	{
		return new ApplicationResponse(FundtransferConstants.TRANSFERED_SUCCESS);
	}
}
