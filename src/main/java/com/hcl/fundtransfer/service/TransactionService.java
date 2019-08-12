package com.hcl.fundtransfer.service;

import java.util.List;

import com.hcl.fundtransfer.DTO.ApplicationResponse;
import com.hcl.fundtransfer.DTO.FundtransferDto;
import com.hcl.fundtransfer.DTO.TransactionDto;

public interface TransactionService {
	
	ApplicationResponse doFundTransfer(FundtransferDto fundTransferDto);
	
	List<TransactionDto> getTransacions(Long accountNumber);

}
