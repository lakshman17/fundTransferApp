package com.hcl.fundtransfer.util;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.service.PayeeServiceImpl;

@Component
public class EmailSender {

	public static final Logger LOGGER = LoggerFactory.getLogger(PayeeServiceImpl.class);
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	RestTemplate restTemplate;

	public String sendOtp(String mailId,Long otpNumber) {

		LOGGER.info("Enter send mail");

		String returnString = "Email sent sucess";
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setTo(mailId);
			helper.setSubject("Otp verification");
			helper.setText("This is OTP for adding payee : " + otpNumber);

			mailSender.send(message);

		} catch (Exception e) {
			returnString = "Mail failed";
			LOGGER.error(e.getMessage());
		}
		return returnString;

	}

	

}
