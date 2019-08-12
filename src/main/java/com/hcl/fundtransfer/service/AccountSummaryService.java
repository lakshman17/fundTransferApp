package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.DTO.AccountSummaryReponse;

public interface AccountSummaryService {
	
	AccountSummaryReponse getAccountSummary(Long accountNumber);;

}
