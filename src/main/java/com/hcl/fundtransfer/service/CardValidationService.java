package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.CardValidationRequestDto;
import com.hcl.fundtransfer.dto.CardValidationResponseDto;

public interface CardValidationService {
	
	CardValidationResponseDto getCardValidationDetails(CardValidationRequestDto cardValidationRequestDto);

}
