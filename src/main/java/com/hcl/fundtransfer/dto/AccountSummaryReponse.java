package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AccountSummaryReponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private String accountNumber;
	private Double closingBalance;

}
