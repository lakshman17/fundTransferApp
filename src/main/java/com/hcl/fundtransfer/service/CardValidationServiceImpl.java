package com.hcl.fundtransfer.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
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
import com.hcl.fundtransfer.exception.CommonException;
import com.hcl.fundtransfer.repository.CardOtpRepository;
import com.hcl.fundtransfer.repository.CardValidationRepository;
import com.hcl.fundtransfer.util.DateUtil;

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

		String stringWithoutSpaces = cardValidationRequestDto.getNumber().replaceAll("\\s+", "");
		Optional<CardDetails> cardDetails = cardValidationRepository
				.findByCardNumber(Long.valueOf(stringWithoutSpaces));
		if (!cardDetails.isPresent())
			throw new CommonException("Please enter valid card ");

		if (!cardDetails.get().getCardNumber().equals(Long.valueOf(stringWithoutSpaces)))
			throw new CommonException("Please enter valid cardNumber");

		if (!cardDetails.get().getCardName().equals(cardValidationRequestDto.getName()))
			throw new CommonException("Please enter valid username");

		if (!cardDetails.get().getCvv().equals(cardValidationRequestDto.getCvc()))
			throw new CommonException("Please enter valid cvv");
		
		String date=DateUtil.convertDate(cardDetails.get().getValidTo().toString(), "yyyy-MM-dd", "MM/yy");

		if (!date.equals(cardValidationRequestDto.getExpiry()))
			throw new CommonException("Please enter valid thru");
		
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
		String inputString="1234 1234 1234 1234";
		String stringWithoutSpaces = inputString.replaceAll("\\s+", "");
        
        System.out.println("Input String : "+Long.valueOf(stringWithoutSpaces));
        
//        System.out.println(DateUtil.convertDate("2019-08-06", "yyyy-MM-dd", "MM/yy"));
        System.out.println(DateUtil.convertDate("2019-08-06", "yyyy-MM-dd", "yyyy-MM"));
        
        String string = "January  2010";
        DateFormat format = new SimpleDateFormat("MMMM  yyyy", Locale.ENGLISH);
        Date date = format.parse(string);
        System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
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
