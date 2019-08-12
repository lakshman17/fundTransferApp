package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Transaction;
import com.hcl.fundtransfer.exception.AccountNumberException;
import com.hcl.fundtransfer.repository.AccountRepository;
import com.hcl.fundtransfer.repository.CustomerRepository;
import com.hcl.fundtransfer.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public ApplicationResponse doFundTransfer(FundtransferDto fundTransferDto) {
		LOGGER.info("fundtransfer service impl");

		Optional<Account> fromAccount = accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber());
		Optional<Account> toAccount = accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber());

		if (!fromAccount.isPresent())
			throw new AccountNumberException(FundtransferConstants.ERROR_FROM_ACCOUNT_NUMBER_MESSAGE);
		if (!toAccount.isPresent())
			throw new AccountNumberException(FundtransferConstants.ERROR_TO_ACCOUNT_NUMBER_MESSAGE);
		if (fundTransferDto.getAmount() <= 0)
			throw new AccountNumberException(FundtransferConstants.ERROR_AMOUNT_GREATERTHAN);
		if (fromAccount.get().getBalance() < fundTransferDto.getAmount())
			throw new AccountNumberException(FundtransferConstants.ERROR_TO_INUFFICIENT_BALANCE);

		// Debit Transaction
		Transaction debitTransaction = new Transaction();
		debitTransaction.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		debitTransaction.setToAccountNo(fundTransferDto.getToAccountNumber());
		debitTransaction.setAmount(fundTransferDto.getAmount());
		double debitAmount = fromAccount.get().getBalance() - fundTransferDto.getAmount();
		debitTransaction.setTransactionType("debit");
		debitTransaction.setAccount(fromAccount.get());
		debitTransaction.setCustomer(fromAccount.get().getCustomer());
		debitTransaction.setComment(fundTransferDto.getComment());
		
		// Account saving
		fromAccount.get().setBalance(debitAmount);
		accountRepository.save(fromAccount.get());
		debitTransaction.setClosingBalance(fromAccount.get().getBalance());
		transactionRepository.save(debitTransaction);

		// Credit Transaction
		Transaction creditTransaction = new Transaction();
		creditTransaction.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		creditTransaction.setToAccountNo(fundTransferDto.getToAccountNumber());
		creditTransaction.setAmount(fundTransferDto.getAmount());
		double creditAmount = toAccount.get().getBalance() + fundTransferDto.getAmount();
		creditTransaction.setTransactionType("credit");
		creditTransaction.setAccount(toAccount.get());
		creditTransaction.setCustomer(toAccount.get().getCustomer());
		creditTransaction.setComment(fundTransferDto.getComment());
		// Account saving
		toAccount.get().setBalance(creditAmount);
		accountRepository.save(toAccount.get());
		creditTransaction.setClosingBalance(toAccount.get().getBalance());
		transactionRepository.save(creditTransaction);
		
		return new ApplicationResponse(FundtransferConstants.TRANSFERED_SUCCESS);
	}

}
