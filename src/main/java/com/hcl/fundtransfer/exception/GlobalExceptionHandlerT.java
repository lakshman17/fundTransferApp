package com.hcl.fundtransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hcl.fundtransfer.DTO.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandlerT {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> employeeException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccountNumberException.class)
	public ResponseEntity<ErrorResponse> accountNumberException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ErrorResponse> commonException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InSufficientFundsException.class)
	public ResponseEntity<ErrorResponse> insufficientFundException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
