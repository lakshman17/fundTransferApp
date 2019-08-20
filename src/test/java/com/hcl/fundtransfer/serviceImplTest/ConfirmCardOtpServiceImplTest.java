/**
 * 
 */
package com.hcl.fundtransfer.serviceImplTest;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.fundtransfer.dto.ConfirmOtpRequestDto;
import com.hcl.fundtransfer.dto.ConfirmOtpResponseDto;
import com.hcl.fundtransfer.entity.CardDetails;
import com.hcl.fundtransfer.entity.CreditOtp;
import com.hcl.fundtransfer.entity.Purchase;
import com.hcl.fundtransfer.repository.CreditCardRepository;
import com.hcl.fundtransfer.repository.CreditOtpRepository;
import com.hcl.fundtransfer.repository.PurchaseRepository;
import com.hcl.fundtransfer.service.ConfirmCardOtpService;

/**
 * @author user1
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfirmCardOtpServiceImplTest {

	@InjectMocks
	ConfirmCardOtpService confrimCardOtpService;
	
	@Mock
	CreditCardRepository creditCardRepository;
	
	@Mock
	PurchaseRepository purchaseRepository;
	
	@Mock
	CreditOtpRepository creditOtpRepository;
	
	ConfirmOtpRequestDto confirmOtpRequestDto;
	ConfirmOtpResponseDto confirmOtpResponseDto;
	CreditOtp creditOtp;
	CardDetails cardDetails;
	Purchase purchase;
	
	@Before
	public void setup() {
		confirmOtpRequestDto = new ConfirmOtpRequestDto();
		confirmOtpRequestDto.setCardId(2);
		
		creditOtp = new CreditOtp();
		creditOtp.setOtpId(1);
		
		confirmOtpResponseDto=new ConfirmOtpResponseDto();
		confirmOtpResponseDto.setMessage("otp verified successfully");
	
		cardDetails = new CardDetails();
		cardDetails.setCardName("Gurpreet Singh");
		
		purchase= new Purchase();
		purchase.setPrice(2.0D);
	}
	
	public void testConfirmOtp()
	{
		Mockito.when(creditOtpRepository.findByOtpNumber(confirmOtpRequestDto.getOtpNumber())).thenReturn(Optional.of(creditOtp));
		Mockito.when(creditCardRepository.findById(confirmOtpRequestDto.getCardId())).thenReturn(Optional.of(cardDetails));
		Mockito.when(purchaseRepository.save(Mockito.any())).thenReturn(purchase);
		Mockito.when(creditOtpRepository.save(Mockito.any())).thenReturn(creditOtp);
		confirmOtpResponseDto = confrimCardOtpService.confirmOtp(confirmOtpRequestDto);
		assertEquals("Otp verified successfully", confirmOtpResponseDto);
	}
	
}
 