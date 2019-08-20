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
import com.hcl.fundtransfer.dto.CardValidationRequestDto;
import com.hcl.fundtransfer.service.CardValidationService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration

public class ConfirmCardValidationControllerTest {

	@Mock
	CardValidationService cardValidationService;

	@InjectMocks
	CardValidationController cardValidationController;

	MockMvc mockMvc;

	CardValidationRequestDto cardValidationRequestDto;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(cardValidationController).build();
		cardValidationRequestDto = getCard();
	}

	@Test
	public void getCardValidation() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/cardValidation").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cardValidationRequestDto))).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public CardValidationRequestDto getCard() {
		return new CardValidationRequestDto("12341234123412342","08/19", 124, "priya");
	}
}
