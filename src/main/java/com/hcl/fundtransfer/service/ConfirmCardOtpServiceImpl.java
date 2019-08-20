/**
 * 
 */
package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.dto.ConfirmOtpRequestDto;
import com.hcl.fundtransfer.dto.ConfirmOtpResponseDto;
import com.hcl.fundtransfer.entity.CardDetails;
import com.hcl.fundtransfer.entity.CreditOtp;
import com.hcl.fundtransfer.entity.Purchase;
import com.hcl.fundtransfer.exception.CardNotFoundException;
import com.hcl.fundtransfer.exception.InSufficientFundsException;
import com.hcl.fundtransfer.exception.OtpNotFoundException;
import com.hcl.fundtransfer.exception.PurchaseNotFoundException;
import com.hcl.fundtransfer.repository.CreditCardRepository;
import com.hcl.fundtransfer.repository.CreditOtpRepository;
import com.hcl.fundtransfer.repository.PurchaseRepository;

/**
 * @author user1
 *
 */
@Service
public class ConfirmCardOtpServiceImpl implements ConfirmCardOtpService {
	
	@Autowired
	CreditOtpRepository creditOtprepository;
	
	@Autowired
	CreditCardRepository creditCardrepository;
	
	@Autowired
	PurchaseRepository purchaserepository;

	/**
	 * This method is use to confirm the credit card Otp
	 */
	@Override
	public ConfirmOtpResponseDto confirmOtp(ConfirmOtpRequestDto confirmOtpRequestDto) {
		Optional<CreditOtp> otp = creditOtprepository.findByOtpNumber(confirmOtpRequestDto.getOtpNumber());
		Optional<CardDetails> carddetails = creditCardrepository.findById(confirmOtpRequestDto.getCardId());
		//Optional<Purchase> purchase = purchaserepository.findById(confirmOtpRequestDto.getCardId());
		//Purchase purch= purchase.get();
		if(!carddetails.isPresent())
			throw new CardNotFoundException("card not found");
		if(!otp.isPresent())
			throw new OtpNotFoundException("no otp found");
		if(!otp.get().getOtpNumber().equals(confirmOtpRequestDto.getOtpNumber()))
			throw new OtpNotFoundException("please enter valid otp");
		if (carddetails.get().getCardLimit() < confirmOtpRequestDto.getPrice())
			throw new InSufficientFundsException(FundtransferConstants.ERROR_TO_INUFFICIENT_BALANCE);
		
		Purchase purch= new Purchase();
		purch.setCardId(confirmOtpRequestDto.getCardId());
		purch.setPrice(confirmOtpRequestDto.getPrice());
		purch.setStatus("purchased");
		purch.setTransactionType("Debit");
		purchaserepository.save(purch);
		if(carddetails.get().getCardLimit()<=0)
			throw new InSufficientFundsException("No sufficient balance");
		double debitamount = carddetails.get().getCardLimit()-confirmOtpRequestDto.getPrice();
		carddetails.get().setCardLimit(debitamount);
		purchaserepository.save(purch);
		creditOtprepository.save(otp.get());
		
  		return new ConfirmOtpResponseDto("Otp verified successfully");
	}

}
