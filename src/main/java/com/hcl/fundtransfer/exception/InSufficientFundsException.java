package com.hcl.fundtransfer.exception;


public class InSufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InSufficientFundsException() {
		super();
	}
	
	public InSufficientFundsException(final String message) {
		super(message);
	}
	
	
}
