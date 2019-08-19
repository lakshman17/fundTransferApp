package com.hcl.fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;
import com.hcl.fundtransfer.dto.PayeeUpdateRequestDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Payee;
import com.hcl.fundtransfer.exception.PayeeException;
import com.hcl.fundtransfer.exception.PayeeNotFoundException;
import com.hcl.fundtransfer.repository.PayeeRepository;

@Service
public class PayeeServiceImpl implements PayeeService {

	@Autowired
	PayeeRepository payeeRepository;

	Payee payee = new Payee();
	PayeeResponseDto response = new PayeeResponseDto();

	@Override
	public PayeeResponseDto createPayee(PayeeRequestDto request) {
		Customer customer = new Customer();
		customer.setCustomerId(request.getCustomerId());
		Optional<Payee> payee1 = payeeRepository.findByPayeeAccountNumberAndStatus(request.getPayeeAccountNumber(),
				"pending");
		if (payee1.isPresent()) {
			throw new PayeeException();
		} else {
			BeanUtils.copyProperties(request, payee);
			payee.setStatus("pending");
			payee.setCustomer(customer);
			payeeRepository.save(payee);
			response.setMessage("Payee added successfully");
			return response;
		}
	}

	@Override
	public PayeeResponseDto updatePayee(Integer payeeId, PayeeUpdateRequestDto request) {
		Payee payeeDetail = payeeRepository.findById(payeeId).orElse(null);
		if (payeeDetail==null) {
			throw new PayeeNotFoundException(payeeId);
		} else {
			BeanUtils.copyProperties(request, payeeDetail);
			payeeRepository.save(payeeDetail);
			response.setPayeeId(payeeId);
			response.setMessage("Payee updated successfully");
			return response;
		}
	}

	@Override
	public PayeeResponseDto deletePayee(Integer payeeId) {
		Optional<Payee> payeeDetail = payeeRepository.findById(payeeId);
		if (!payeeDetail.isPresent()) {
			throw new PayeeNotFoundException(payeeId);
		}
		else {
			payeeRepository.deleteById(payeeId);
			response.setMessage("Payee deleted successfully");
			response.setPayeeId(payeeId);
			return response;
		}
		
	}

}
