package com.hcl.fundtransfer.exception;

import java.io.Serializable;

public class StatementException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Statements Not Found for id: ";

	public StatementException(Integer cardId) {
		super(MESSAGE +cardId);

	}

}
