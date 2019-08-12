package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;

public interface TransactionService {
	
	ApplicationResponse doFundTransfer(FundtransferDto fundTransferDto);

}
