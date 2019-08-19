package com.hcl.fundtransfer.serviceImplTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.dto.TransactionDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Transaction;
import com.hcl.fundtransfer.exception.AccountNumberException;
import com.hcl.fundtransfer.exception.CommonException;
import com.hcl.fundtransfer.exception.InSufficientFundsException;
import com.hcl.fundtransfer.repository.IAccountRepository;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.repository.TransactionRepository;
import com.hcl.fundtransfer.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
	@Mock
	TransactionRepository transacionRepository;

	@Mock
	IAccountRepository accountRepository;

	@Mock
	ICustomerRepository cusomerRepository;

	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;

	Customer customer1;
	Customer customer2;
	Account fromAccount;
	Account toAccount;
	FundtransferDto fundTransferDto;
	Transaction debitTransaction;
	Transaction creditTransaction;
	double debitAmount;
	double creditAmount;
	TransactionDto transactionDto;

	@Before
	public void setUp() {
		customer1 = getCustomer1();
		customer2 = getCustomer2();
		fromAccount = getAccount1();
		toAccount = getAccount2();
		fundTransferDto = getFundTransferDto();
		transactionDto = getTransactionDto();
	}

	@Test
	public void fundTranserTest() {

		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber()))
				.thenReturn(Optional.of(fromAccount));
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber()))
				.thenReturn(Optional.of(toAccount));

		debitTransaction = getTransaction1();
		fromAccount.setBalance(debitAmount);
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(fromAccount);
		debitTransaction.setClosingBalance(fromAccount.getBalance());
		Mockito.when(transacionRepository.save(Mockito.any())).thenReturn(debitTransaction);

		creditTransaction = getTransaction2();
		toAccount.setBalance(creditAmount);
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(toAccount);
		creditTransaction.setClosingBalance(toAccount.getBalance());
		Mockito.when(transacionRepository.save(Mockito.any())).thenReturn(creditTransaction);

		ApplicationResponse applicationResponse = transactionServiceImpl.doFundTransfer(fundTransferDto);
		Assert.assertEquals(FundtransferConstants.TRANSFERED_SUCCESS, applicationResponse.getMessage());

	}

	@Test(expected = AccountNumberException.class)
	public void fundTransferFromAccountNumberTest() {

		fundTransferDto.setFromAccountNumber("1L");
		transactionServiceImpl.doFundTransfer(fundTransferDto);
	}

	@Test(expected = AccountNumberException.class)
	public void fundTransferToAccountNumberTest() {
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber()))
				.thenReturn(Optional.of(fromAccount));
		fundTransferDto.setToAccountNumber("1L");
		transactionServiceImpl.doFundTransfer(fundTransferDto);
	}

	@Test(expected = CommonException.class)
	public void fundTransferAmountZeroTest() {
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber()))
				.thenReturn(Optional.of(fromAccount));
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber()))
				.thenReturn(Optional.of(toAccount));
		fundTransferDto.setAmount(0.0);
		transactionServiceImpl.doFundTransfer(fundTransferDto);
	}

	@Test(expected = InSufficientFundsException.class)
	public void fundTransferInsufficientBalanceTest() {
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber()))
				.thenReturn(Optional.of(fromAccount));
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber()))
				.thenReturn(Optional.of(toAccount));
		fundTransferDto.setAmount(10000.00);
		transactionServiceImpl.doFundTransfer(fundTransferDto);
	}

	@Test(expected = AccountNumberException.class)
	public void fundTransferFromAndToAccountNumberTest() {
		fundTransferDto.setToAccountNumber("1234L");
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber()))
				.thenReturn(Optional.of(fromAccount));
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber()))
				.thenReturn(Optional.of(toAccount));

		transactionServiceImpl.doFundTransfer(fundTransferDto);

	}

	@Test
	public void getTransactionsTest() {
		List<TransactionDto> transactionDtoList = new ArrayList<>();
		transactionDtoList.add(transactionDto);

		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(getTransaction1());
		Mockito.when(accountRepository.findByAccountNumber("1234L"))
		.thenReturn(Optional.of(fromAccount));
		Mockito.when(transacionRepository.findByFromAccountNo(Mockito.anyString(), Mockito.any())).thenReturn(transactionList);
		List<TransactionDto> expectedList = transactionServiceImpl.getTransacions("1234L");
		Assert.assertEquals(transactionDtoList.size(), expectedList.size());

	}
	
	@Test(expected = AccountNumberException.class)
	public void getTransactionsAccountNumberTest() {
		fundTransferDto.setToAccountNumber("1L");
		transactionServiceImpl.getTransacions("1234L");
	}

	public Transaction getTransaction1() {

		Transaction debitTransaction = new Transaction();
		debitTransaction.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		debitTransaction.setToAccountNo(fundTransferDto.getToAccountNumber());
		debitTransaction.setAmount(fundTransferDto.getAmount());
		debitAmount = fromAccount.getBalance() - fundTransferDto.getAmount();
		debitTransaction.setTransactionType("debit");
		debitTransaction.setAccount(fromAccount);
		debitTransaction.setCustomer(fromAccount.getCustomer());
		debitTransaction.setComment(fundTransferDto.getComment());
		return debitTransaction;
	}

	public Transaction getTransaction2() {
		Transaction creditTransaction = new Transaction();
		creditTransaction.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		creditTransaction.setToAccountNo(fundTransferDto.getToAccountNumber());
		creditTransaction.setAmount(fundTransferDto.getAmount());
		creditAmount = toAccount.getBalance() + fundTransferDto.getAmount();
		creditTransaction.setTransactionType("credit");
		creditTransaction.setAccount(toAccount);
		creditTransaction.setCustomer(toAccount.getCustomer());
		creditTransaction.setComment(fundTransferDto.getComment());
		return creditTransaction;
	}

	public Account getAccount1() {
		Account account = new Account();
		account.setAccountId(1);
		account.setAccountNumber("1234L");
		account.setBalance(1000.00);
		account.setCustomer(getCustomer1());
		return account;

	}

	public Account getAccount2() {
		Account account = new Account();
		account.setAccountId(2);
		account.setAccountNumber("5678L");
		account.setBalance(100.00);
		account.setCustomer(getCustomer2());
		return account;
	}

	public Customer getCustomer1() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("priya");
		customer.setLastName("kumar");
		customer.setMobileNumber(123456789L);
		customer.setPassword("abc");
		return customer;

	}

	public Customer getCustomer2() {
		Customer customer = new Customer();
		customer.setCustomerId(2);
		customer.setFirstName("hari");
		customer.setLastName("naidu");
		customer.setMobileNumber(123456789L);
		customer.setPassword("abc");
		return customer;

	}

	public FundtransferDto getFundTransferDto() {
		FundtransferDto fundTransferDto = new FundtransferDto();
		fundTransferDto.setAmount(100.0);
		fundTransferDto.setFromAccountNumber("1234L");
		fundTransferDto.setToAccountNumber("5678L");
		return fundTransferDto;
	}

	public TransactionDto getTransactionDto() {
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionId(1);
		transactionDto.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		transactionDto.setToAccountNo(fundTransferDto.getToAccountNumber());
		transactionDto.setAmount(fundTransferDto.getAmount());
		transactionDto.setTransactionType("credit");
		transactionDto.setClosingBalance(20000.00);
		transactionDto.setComment("rent");
		return transactionDto;
	}

}
