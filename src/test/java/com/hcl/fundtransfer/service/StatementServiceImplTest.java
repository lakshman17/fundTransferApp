package com.hcl.fundtransfer.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.fundtransfer.dto.StatementResponseDto;
import com.hcl.fundtransfer.entity.Purchase;
import com.hcl.fundtransfer.repository.PurchaseRepository;

@RunWith(MockitoJUnitRunner.class)
public class StatementServiceImplTest {

	@Mock
	PurchaseRepository purchaseRepository;
	@InjectMocks
	StatementServiceImpl statementServiceImpl;

	List<StatementResponseDto> allStatements;
	StatementResponseDto response;
	List<Purchase> statementList;
	Purchase purchase;

	@Before
	public void setUp() {
		allStatements = new ArrayList<>();
		response = new StatementResponseDto();
		statementList = new ArrayList<>();
		purchase = new Purchase();
		purchase.setPurchaseId(1);
		purchase.setPurchaseDate(LocalDate.now());
		purchase.setPrice(200.00);
		statementList.add(purchase);
		response.setPurchaseId(1);
		response.setPrice(200.00);
		allStatements.add(response);
		String splitString = purchase.toString().substring(0,7);
	}

	@Test
	public void testGetAllStatements() {
		Mockito.when(purchaseRepository.findAllById(Mockito.anyInt())).thenReturn(statementList);
		List<StatementResponseDto> statementDetails = statementServiceImpl.getAllStatements(1,"2019-08");
		Assert.assertEquals(allStatements.get(0).getPurchaseId(), statementDetails.get(0).getPurchaseId());

	}

}
