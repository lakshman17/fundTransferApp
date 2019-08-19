package com.hcl.fundtransfer.service;

import java.util.List;

import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.dto.TransactionDto;

public interface TransactionService {
	
	ApplicationResponse doFundTransfer(FundtransferDto fundTransferDto);
	
	List<TransactionDto> getTransacions(String accountNumber);

}
