package com.hcl.fundtransfer.serviceImplTest;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.fundtransfer.dto.FundtransferDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Transaction;
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

	@Before
	public void setUp() {
		customer1 = getCustomer1();
		customer2 = getCustomer2();
		fromAccount = getAccount1();
		toAccount = getAccount2();
		fundTransferDto = getFundTransferDto();
	}

	@Test
	public void fundTranserTest() {

		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber()))
				.thenReturn(Optional.of(fromAccount));
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber()))
				.thenReturn(Optional.of(toAccount));

		Transaction debitTransaction = new Transaction();
		debitTransaction.setFromAccountNo(fundTransferDto.getFromAccountNumber());
		debitTransaction.setToAccountNo(fundTransferDto.getToAccountNumber());
		debitTransaction.setAmount(fundTransferDto.getAmount());
		double debitAmount = fromAccount.getBalance() - fundTransferDto.getAmount();
		debitTransaction.setTransactionType("debit");
		debitTransaction.setAccount(fromAccount);
		debitTransaction.setCustomer(fromAccount.getCustomer());
		debitTransaction.setComment(fundTransferDto.getComment());

		// Account saving
		fromAccount.setBalance(debitAmount);
//		Mockito.when(accountRepository.save(fromAccount)).thenReturn(Optional.of(fromAccount));
//		debitTransaction.setClosingBalance(fromAccount.get().getBalance());
//		transactionRepository.save(debitTransaction);

	}

	public FundtransferDto getFundTransferDto() {
		FundtransferDto fundTransferDto = new FundtransferDto();
		fundTransferDto.setAmount(10000.0);
		fundTransferDto.setToAccountNumber(1234L);
		fundTransferDto.setToAccountNumber(5678L);
		return fundTransferDto;
	}

	public Transaction getTransaction1() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		transaction.setFromAccountNo(1234L);
		transaction.setToAccountNo(5678L);
		transaction.setTransactionType("debit");
		return transaction;
	}

	public Account getAccount1() {
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber(1234L);
		account.setBalance(1000.00);
		account.setCustomer(getCustomer1());
		return account;

	}

	public Account getAccount2() {
		Account account = new Account();
		account.setAccountId(2L);
		account.setAccountNumber(56789L);
		account.setBalance(100.00);
		account.setCustomer(getCustomer2());
		return account;
	}

	public Customer getCustomer1() {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("priya");
		customer.setLastName("kumar");
		customer.setMobileNumber(123456789L);
		customer.setPassword("abc");
		return customer;

	}

	public Customer getCustomer2() {
		Customer customer = new Customer();
		customer.setCustomerId(2L);
		customer.setFirstName("hari");
		customer.setLastName("naidu");
		customer.setMobileNumber(123456789L);
		customer.setPassword("abc");
		return customer;

	}

}
