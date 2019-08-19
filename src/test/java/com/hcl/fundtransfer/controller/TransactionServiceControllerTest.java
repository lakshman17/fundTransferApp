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
import com.hcl.fundtransfer.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration

public class TransactionServiceControllerTest {

	@Mock
	TransactionService transactionService;

	@InjectMocks
	TransactionController transactionController;

	MockMvc mockMvc;
	
	FundtransferDto fundTransferDto;
	ApplicationResponse applicationResponse;

	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
		fundTransferDto = getFundTransferDto();
		applicationResponse=getApplicationResponse();
	}

	@Test
	public void doFundTransfer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/fundtransfer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(fundTransferDto)))
				.andExpect(status().isCreated());

	}
	
	@Test
	public void getTransactions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/viewTransactions/{accountNumber}",1234L).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(fundTransferDto)))
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
		fundTransferDto.setFromAccountNumber("5678L");
		fundTransferDto.setToAccountNumber("1234L");
		
		return fundTransferDto;
	}

	public ApplicationResponse getApplicationResponse()
	{
		return new ApplicationResponse(FundtransferConstants.TRANSFERED_SUCCESS);
	}
}
