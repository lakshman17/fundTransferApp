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
import com.hcl.fundtransfer.repository.AccountRepository;
import com.hcl.fundtransfer.repository.CustomerRepository;
import com.hcl.fundtransfer.repository.TransactionRepository;
import com.hcl.fundtransfer.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
	@Mock
	TransactionRepository transacionRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository cusomerRepository;

	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	Customer customer1;
	Customer customer2;
	Account account1;
	Account account2;
	FundtransferDto fundTransferDto;

	@Before
	public void setUp() {
		customer1 = getCustomer1();
		customer2 = getCustomer2();
		account1 = getAccount1();
		account2 = getAccount2();
		fundTransferDto=getFundTransferDto();
	}

	@Test
	public void fundTranserTest() {
		
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber())).thenReturn(Optional.of(account1));
		Mockito.when(accountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber())).thenReturn(Optional.of(account2));
		
	//	Transaction transaction=new Te

	}

	public FundtransferDto getFundTransferDto()
	{
		FundtransferDto fundTransferDto=new FundtransferDto();
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
