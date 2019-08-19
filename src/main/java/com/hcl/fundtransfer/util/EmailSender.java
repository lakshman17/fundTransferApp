package com.hcl.fundtransfer.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.dto.OtpRequestDto;
import com.hcl.fundtransfer.dto.OtpResponseDto;
import com.hcl.fundtransfer.repository.ICustomerRepository;
import com.hcl.fundtransfer.repository.OtpRepository;

@Component
public class EmailSender {
	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	RestTemplate restTemplate;

	public String sendOtp() throws Exception {
		try {
		//	OtpRequestDto request
			OtpResponseDto response = getOtp();

			SimpleMailMessage message = new SimpleMailMessage();
//
//			Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
//			if (!customer.isPresent())
//				throw new CustomerNotFoundException("Customer not found");

			message.setTo("deepsiva33@gmail.com");
			message.setSubject("OTP verification");
//			Random random = new Random();
//			int otp = random.nextInt(10000);
//			Long userOtp = Long.valueOf("" + otp);
			Long userOtp = response.getOtpNumber();
			message.setText("This is OTP for adding payee : " + userOtp);
//			OtpDetails findByAccountNo = otpRepository.findByAccountNo(request.getAccountNo());
//			if (findByAccountNo != null) {
//				findByAccountNo.setOtp(userOtp);
//				findByAccountNo.setOtpUsed('F');
//				message.setText("This is OTP for adding payee : " + userOtp);
//			} else {
//				OtpDetails otpDetails = new OtpDetails();
//				otpDetails.setAccountNo(request.getAccountNo());
//				otpDetails.setOtp(userOtp);
//				otpDetails.setOtpUsed('F');
//				message.setText("This is OTP for adding payee : " + userOtp);
//				otpRepository.save(otpDetails);
//			}

			emailSender.send(message);

		} catch (Exception e) {
		}
		
		return "success";
	}

	private OtpResponseDto getOtp() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:9090/fundtrasfer/api/otp", HttpMethod.GET, entity, OtpResponseDto.class)
				.getBody();

	}

}
