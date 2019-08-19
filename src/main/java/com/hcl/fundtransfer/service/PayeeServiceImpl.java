package com.hcl.fundtransfer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.constants.FundtransferConstants;
import com.hcl.fundtransfer.dto.OtpResponseDto;
import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;
import com.hcl.fundtransfer.dto.PayeeUpdateRequestDto;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Otp;
import com.hcl.fundtransfer.entity.Payee;
import com.hcl.fundtransfer.exception.AccountNumberException;
import com.hcl.fundtransfer.exception.CommonException;
import com.hcl.fundtransfer.exception.CustomerNotFoundException;
import com.hcl.fundtransfer.exception.PayeeException;
import com.hcl.fundtransfer.exception.PayeeNotFoundException;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.repository.OtpRepository;
import com.hcl.fundtransfer.repository.PayeeRepository;
import com.hcl.fundtransfer.util.EmailSender;

@Service
public class PayeeServiceImpl implements PayeeService {

	public static final Logger LOGGER = LoggerFactory.getLogger(PayeeServiceImpl.class);

	@Autowired
	PayeeRepository payeeRepository;

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	EmailSender emailSender;

	Payee payee = new Payee();
	PayeeResponseDto response = new PayeeResponseDto();

	@Override
	public PayeeResponseDto createPayee(PayeeRequestDto request) {
		LOGGER.info("Enter add payee");

		Optional<Payee> payee1 = payeeRepository.findByPayeeAccountNumberAndStatus(request.getPayeeAccountNumber(),
				"pending");
		Optional<Customer> customer = customerRepository.findById(request.getCustomerId());

		if (payee1.isPresent()) {
			throw new PayeeException();
		}

		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found");

		}
		Optional<Customer> customerAccountnumber = customerRepository
				.findByAccountNumber(request.getPayeeAccountNumber());
		if (!customerAccountnumber.isPresent()) {
			throw new CommonException("Please enter correct accountnumber");
		}

		if (customer.get().getAccountNumber().equals(request.getPayeeAccountNumber()))
			throw new AccountNumberException("From and payee account numbers should not be same");
		BeanUtils.copyProperties(request, payee);
		payee.setStatus("pending");
		payee.setCustomer(customer.get());
		Payee payeeDb = payeeRepository.save(payee);

		OtpResponseDto otpResponse = getOtp();
		Otp otp = new Otp();
		otp.setOtpNumber(otpResponse.getOtpNumber());
		otp.setPayee(payeeDb);
		otp.setCustomer(customer.get());
		otpRepository.save(otp);

		emailSender.sendOtp(customer.get().getEmailId(), otpResponse.getOtpNumber());

		return new PayeeResponseDto(payeeDb.getPayeeId(), FundtransferConstants.OTP_SENT);
	}

	private OtpResponseDto getOtp() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		return restTemplate
				.exchange("http://localhost:9090/fundtrasfer/api/otp", HttpMethod.GET, entity, OtpResponseDto.class)
				.getBody();

	}

	public List<PayeeRequestDto> getAllPayees(Integer customerId) {
		List<PayeeRequestDto> allPayees = new ArrayList<>();
		List<Payee> payeeList = payeeRepository.findAllById(customerId);
		if (payeeList.isEmpty()) {
			throw new PayeeNotFoundException(customerId);
		} else {
			payeeList.stream().forEach(p -> {
				PayeeRequestDto payeeDetail = new PayeeRequestDto();
				payeeDetail.setCustomerId(p.getCustomer().getCustomerId());
				BeanUtils.copyProperties(p, payeeDetail);
				allPayees.add(payeeDetail);
			});
			return allPayees;
		}

	}

	@Override
	public PayeeResponseDto updatePayee(Integer payeeId, PayeeUpdateRequestDto request) {
		Payee payeeDetail = payeeRepository.findById(payeeId).orElse(null);
		Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
		Optional<Otp> optDb = otpRepository.getPayeeOtpNumber(payeeId);

		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found exception");
		}
		if (payeeDetail == null) {
			throw new PayeeNotFoundException(payeeId);
		} else {
			payeeDetail.setStatus("Pending");
			BeanUtils.copyProperties(request, payeeDetail);
			Payee payeeDb = payeeRepository.save(payeeDetail);
			OtpResponseDto otpResponse = getOtp();
			if (optDb.isPresent()) {
				optDb.get().setOtpNumber(otpResponse.getOtpNumber());
				optDb.get().setPayee(payeeDb);
				optDb.get().setCustomer(customer.get());
				otpRepository.save(optDb.get());
			} else {
				Otp otp = new Otp();
				otp.setOtpNumber(otpResponse.getOtpNumber());
				otp.setPayee(payeeDb);
				otp.setCustomer(customer.get());
				otpRepository.save(otp);
			}

			response.setPayeeId(payeeId);
			response.setMessage(FundtransferConstants.OTP_SENT);

			return response;
		}
	}

	@Override
	public PayeeResponseDto deletePayee(Integer payeeId, Integer customerId) {
		Optional<Payee> payeeDetail = payeeRepository.findById(payeeId);
		Optional<Otp> optDb = otpRepository.getPayeeOtpNumber(payeeId);
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found ");
		}
		if (!payeeDetail.isPresent()) {
			throw new PayeeNotFoundException(payeeId);
		} else {
			payeeDetail.get().setStatus("Pending");
			payeeRepository.save(payeeDetail.get());

			OtpResponseDto otpResponse = getOtp();
			if (optDb.isPresent()) {
				optDb.get().setOtpNumber(otpResponse.getOtpNumber());
				optDb.get().setPayee(payeeDetail.get());
				optDb.get().setCustomer(customer.get());
				otpRepository.save(optDb.get());

			} else {
				Otp otp = new Otp();
				otp.setOtpNumber(otpResponse.getOtpNumber());
				otp.setPayee(payeeDetail.get());
				otp.setCustomer(customer.get());
				otpRepository.save(otp);
			}

			response.setPayeeId(payeeId);
			response.setMessage(FundtransferConstants.OTP_SENT);

			return response;
		}

	}

}
