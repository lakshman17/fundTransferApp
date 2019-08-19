package com.hcl.fundtransfer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.dto.TransactionDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Transaction;
import com.hcl.fundtransfer.entity.TransactionType;
import com.hcl.fundtransfer.exception.AccountNumberException;
import com.hcl.fundtransfer.exception.CommonException;
import com.hcl.fundtransfer.exception.InSufficientFundsException;
import com.hcl.fundtransfer.repository.IAccountRepository;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.repository.PayeeRepository;
import com.hcl.fundtransfer.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	IAccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	PayeeRepository payeeRepository;

	/**
	 * @author HariPriya G This method explains transfer the amount fromAccount to
	 *         toAccount number
	 * @param fundTransferDto it is the request object which contains
	 *                        fromAccountNumber,toAccountNumber,amount and remarks
	 * @return it returns "transfered success" message
	 * 
	 */

	@Override
	public ApplicationResponse doFundTransfer(FundtransferDto fundTransferDto) {
		LOGGER.info("fundtransfer service impl");

		Optional<Account> fromAccount = accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber());
		Optional<Account> toAccount = accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber());

		if (!fromAccount.isPresent())
			throw new AccountNumberException(FundtransferConstants.ERROR_FROM_ACCOUNT_NUMBER_MESSAGE);
		if (!toAccount.isPresent())
			throw new AccountNumberException(FundtransferConstants.ERROR_TO_ACCOUNT_NUMBER_MESSAGE);
		if (fundTransferDto.getFromAccountNumber().equals(fundTransferDto.getToAccountNumber()))
			throw new AccountNumberException(FundtransferConstants.ERROR_TO_FROM_ACCOUNT);
		if (fundTransferDto.getAmount() <= 0)
			throw new CommonException(FundtransferConstants.ERROR_AMOUNT_GREATERTHAN);
		if (fromAccount.get().getBalance() < fundTransferDto.getAmount())
			throw new InSufficientFundsException(FundtransferConstants.ERROR_TO_INUFFICIENT_BALANCE);

		// Debit Transaction
		Transaction debitTransaction = new Transaction();
		debitTransaction.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		debitTransaction.setToAccountNo(fundTransferDto.getToAccountNumber());
		debitTransaction.setAmount(fundTransferDto.getAmount());
		double debitAmount = fromAccount.get().getBalance() - fundTransferDto.getAmount();
		debitTransaction.setTransactionType(TransactionType.DEBIT.toString());
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
		creditTransaction.setTransactionType(TransactionType.CREDIT.toString());
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

	/**
	 * @author Attish Raj This method will show the list of transactions based on
	 *         accountNumber *
	 * @param accountNumber it is the request object which contains already logined
	 *                      person accountNumber
	 * @return it returns list of transactions
	 * 
	 */

	@Override
	public List<TransactionDto> getTransacions(String accountNumber) {

		Optional<Account> fromAccount = accountRepository.findByAccountNumber(accountNumber);
		if (!fromAccount.isPresent())
			throw new AccountNumberException(FundtransferConstants.ERROR_ACCOUNT_NUMBER_MESSAGE);
		Pageable pageable = PageRequest.of(0, 10);
		List<Transaction> transactionsList = transactionRepository.findByFromAccountNo(accountNumber, pageable);
		List<TransactionDto> transactionDtoList = new ArrayList<>();
		transactionsList.stream().forEach(p -> {
			TransactionDto transactionDto = new TransactionDto();
			BeanUtils.copyProperties(p, transactionDto);
			transactionDtoList.add(transactionDto);
		});
		return transactionDtoList;
	}

}
