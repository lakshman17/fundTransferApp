package com.hcl.fundtransfer.serviceImplTest;

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
import com.hcl.fundtransfer.dto.AccountSummaryReponse;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.exception.AccountNumberException;
import com.hcl.fundtransfer.repository.IAccountRepository;
import com.hcl.fundtransfer.service.AccoutSummaryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountSummaryServiceImplTest {
	@Mock
	IAccountRepository iAccountRepository;

	@InjectMocks
	AccoutSummaryServiceImpl accoutSummaryServiceImpl;

	Account account;

	@Before
	public void setUp() {
		account = getAccount();
	}

	@Test
	public void getAccountSummaryTest() {
		Mockito.when(iAccountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(Optional.of(account));
		AccountSummaryReponse applicationSummaryResponse = accoutSummaryServiceImpl.getAccountSummary("1234L");
		Assert.assertEquals(FundtransferConstants.PLEASE_FIND_ACOCUNT_DETAILS, applicationSummaryResponse.getMessage());
	}
	
	@Test(expected = AccountNumberException.class)
	public void getAccountSummaryAccountNumberTest() {
		account.setAccountNumber("1");
		accoutSummaryServiceImpl.getAccountSummary("1234L");
	}

	public Account getAccount() {
		Account account = new Account();
		account.setAccountId(1);
		account.setAccountNumber("1234L");
		account.setBalance(1000.00);
		return account;
	}
}
