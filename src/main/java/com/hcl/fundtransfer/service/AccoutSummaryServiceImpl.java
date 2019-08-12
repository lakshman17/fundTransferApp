package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.DTO.AccountSummaryReponse;
import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.exception.AccountNumberException;
import com.hcl.fundtransfer.repository.IAccountRepository;

@Service
public class AccoutSummaryServiceImpl implements AccountSummaryService {

	@Autowired
	IAccountRepository iAccountRepository;

	@Override
	public AccountSummaryReponse getAccountSummary(Long accountNumber) {
		Optional<Account> account = iAccountRepository.findByAccountNumber(accountNumber);
		if (!account.isPresent())
			throw new AccountNumberException(FundtransferConstants.ERROR_ACCOUNT_NUMBER_MESSAGE);

		AccountSummaryReponse accountummary = new AccountSummaryReponse();
		accountummary.setMessage(FundtransferConstants.PLEASE_FIND_ACOCUNT_DETAILS);
		accountummary.setAccountNumber(account.get().getAccountNumber());
		accountummary.setClosingBalance(account.get().getBalance());
		return accountummary;

	}

}
