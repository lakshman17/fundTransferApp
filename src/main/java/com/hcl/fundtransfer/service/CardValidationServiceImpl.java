package com.hcl.fundtransfer.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.fundtransfer.dto.CardValidationRequestDto;
import com.hcl.fundtransfer.dto.CardValidationResponseDto;
import com.hcl.fundtransfer.dto.OtpResponseDto;
import com.hcl.fundtransfer.entity.CardDetails;
import com.hcl.fundtransfer.entity.CreditOtp;
import com.hcl.fundtransfer.entity.Otp;
import com.hcl.fundtransfer.exception.CommonException;
import com.hcl.fundtransfer.repository.CardOtpRepository;
import com.hcl.fundtransfer.repository.CardValidationRepository;

@Service
public class CardValidationServiceImpl implements CardValidationService {

	@Autowired
	CardValidationRepository cardValidationRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CardOtpRepository cardOtpRepository;

	@Override
	public CardValidationResponseDto getCardValidationDetails(CardValidationRequestDto cardValidationRequestDto) {

		Optional<CardDetails> cardDetails = cardValidationRepository
				.findByCardNumber(cardValidationRequestDto.getNumber());
		if (!cardDetails.isPresent())
			throw new CommonException("Please enter valid card ");

		if (!cardDetails.get().getCardNumber().equals(cardValidationRequestDto.getNumber()))
			throw new CommonException("Please enter valid cardNumber");

		if (!cardDetails.get().getCardName().equals(cardValidationRequestDto.getName()))
			throw new CommonException("Please enter valid username");

		if (!cardDetails.get().getCvv().equals(cardValidationRequestDto.getCvc()))
			throw new CommonException("Please enter valid cvv");

//		LocalDate dateOfJoiningLocalDate = getLocalDate(cardDetails.get().getValidTo().toString());
//
//		if (!dateOfJoiningLocalDate.equals(cardValidationRequestDto.getValidTo()))
//			throw new CommonException("Please enter valid cvv");
		
		OtpResponseDto otpResponse = getOtp();
		
		CreditOtp otp = new CreditOtp();
		otp.setOtpNumber(otpResponse.getOtpNumber());
		otp.setCardId(cardDetails.get().getCardId());
		cardOtpRepository.save(otp);


		return new CardValidationResponseDto("Otp sent success", cardDetails.get().getCardId());
	}

	public LocalDate getLocalDate(String date) {
		String DATE_FORMAT="yyyy-MM-dd";
//		String DATE_FORMAT = "MM-yyyy";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDate toDate = LocalDate.parse(date, dateTimeFormatter);
		return toDate;

	}
	
	public static void main(String a[]) throws ParseException
	{
		getDate("2019-03-20");
	}
	
	
	
	private static  void getDate(String dateString) throws ParseException
	{
//		SimpleDateFormat sdf=new SimpleDateFormat("MM/yyyy");
//		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
//		String val = null;
//		try {
//			Calendar date=Calendar.getInstance();
//			date.set(Calendar.DATE, 1);
//			date.setTime(sdf1.parse(dateString));
//			val=sdf.parse(dateString).toString();
//			//System.out.println(val);
//			//System.out.println(sdf1.format(date.getTime()));
//			//System.out.println(sdf.format(date.getTime()));
//			//System.out.println(val);
//			//return val;
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		//return val;
		Date todaysDate = new Date();
		//LocalDate toDate = LocalDate.parse(dateString, dateTimeFormatter);
		 DateFormat df = new SimpleDateFormat("MM/yy");
		 
		 String testDateString = df.format(todaysDate);
         System.out.println("String in dd/MM/yyyy format is: " + testDateString);
	}
	
	private OtpResponseDto getOtp() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		return restTemplate
				.exchange("http://localhost:9090/fundtrasfer/api/otp", HttpMethod.GET, entity, OtpResponseDto.class)
				.getBody();

	}

}
