package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.dto.ApplicationResponse;
import com.hcl.fundtransfer.dto.ConfirmPayeeRequestDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Otp;
import com.hcl.fundtransfer.entity.Payee;
import com.hcl.fundtransfer.exception.CommonException;
import com.hcl.fundtransfer.exception.CustomerNotFoundException;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.repository.OtpRepository;
import com.hcl.fundtransfer.repository.PayeeRepository;

@Service
public class ConfirmOtpServiceImpl implements ConfirmOtpService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	PayeeRepository payeeRepository;

	@Autowired
	ICustomerRepository iCustomerRepository;

	@Override
	public ApplicationResponse confirmPayee(ConfirmPayeeRequestDto confirmPayeeRequestDto) {

		String status = null;
		Optional<Payee> payee = payeeRepository.findById(confirmPayeeRequestDto.getPayeeId());
		Optional<Customer> customer = iCustomerRepository.findById(confirmPayeeRequestDto.getCustomerId());
		Optional<Otp> optDb = otpRepository.getPayeeOtpNumber(confirmPayeeRequestDto.getPayeeId());
		if (!payee.isPresent())
			throw new CustomerNotFoundException("payee not found");
		if (!customer.isPresent())
			throw new CustomerNotFoundException("Customer not found");
		if (!optDb.isPresent())
			throw new CustomerNotFoundException("payee not found");
		if (!confirmPayeeRequestDto.getOtpNumber().equals(optDb.get().getOtpNumber()))
			throw new CommonException("Please enter valid otp");

		if (confirmPayeeRequestDto.getStatus().equalsIgnoreCase("add")) {

			payee.get().setStatus("Payee addedd");
			status = "Payee added successfully";
		} else if (confirmPayeeRequestDto.getStatus().equalsIgnoreCase("update")) {

			payee.get().setStatus("Payee updated");
			status = "Payee updated successfully";
		} else {
			payee.get().setStatus("Payee deleted");
			status = "Payee deleted successfully";
		}
		payeeRepository.save(payee.get());

		return new ApplicationResponse(status);

	}

}
