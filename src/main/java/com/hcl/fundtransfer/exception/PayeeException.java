package com.hcl.fundtransfer.exception;

import java.io.Serializable;

public class PayeeException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "You have already added as a payee";

	public PayeeException() {
		super(MESSAGE);

	}

}
