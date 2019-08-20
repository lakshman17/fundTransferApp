/**
 * 
 */
package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.ConfirmOtpRequestDto;
import com.hcl.fundtransfer.dto.ConfirmOtpResponseDto;
import com.hcl.fundtransfer.entity.CardDetails;
import com.hcl.fundtransfer.entity.CreditOtp;
import com.hcl.fundtransfer.entity.Purchase;
import com.hcl.fundtransfer.exception.CardNotFoundException;
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
		Optional<Purchase> purchase = purchaserepository.findByPrice(confirmOtpRequestDto.getPrice());

		if (!carddetails.isPresent())
			throw new CardNotFoundException("card not found");
		if (!purchase.isPresent())
			throw new PurchaseNotFoundException("no price found");
		if (!otp.isPresent())
			throw new OtpNotFoundException("no otp found");
		if (!otp.get().getOtpNumber().equals(confirmOtpRequestDto.getOtpNumber()))
			throw new OtpNotFoundException("please enter valid otp");

		creditOtprepository.save(otp.get());

		return new ConfirmOtpResponseDto("Otp verified successfully");
	}

}
