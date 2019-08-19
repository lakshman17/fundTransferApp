package com.hcl.fundtransfer.exception;

import java.io.Serializable;

public class PayeeNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Payee Not Found for this id: ";

	public PayeeNotFoundException(Integer payeeId) {
		super(MESSAGE +payeeId);

	}

}
