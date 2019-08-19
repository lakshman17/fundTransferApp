package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Payee;
import com.hcl.fundtransfer.exception.PayeeException;
import com.hcl.fundtransfer.repository.PayeeRepository;

@Service
public class PayeeServiceImpl implements PayeeService {

	@Autowired
	PayeeRepository payeeRepository;

	@Override
	public PayeeResponseDto createPayee(PayeeRequestDto request) {
		Payee payee = new Payee();
		Customer customer = new Customer();
		customer.setCustomerId(request.getCustomerId());
		Optional<Payee> payee1 = payeeRepository.findByPayeeAccountNumberAndStatus(request.getPayeeAccountNumber(),"pending");
		if(payee1.isPresent()) {
			throw new PayeeException();
		}
		else {
			BeanUtils.copyProperties(request, payee);
			payee.setStatus("pending");
			payee.setCustomer(customer);
			payeeRepository.save(payee);
			PayeeResponseDto response = new PayeeResponseDto();
			response.setMessage("Payee added successfully");
			return response;
		}
	}

}
