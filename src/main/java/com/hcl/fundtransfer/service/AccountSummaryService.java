package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.AccountSummaryReponse;

public interface AccountSummaryService {
	
	AccountSummaryReponse getAccountSummary(String accountNumber);

}
