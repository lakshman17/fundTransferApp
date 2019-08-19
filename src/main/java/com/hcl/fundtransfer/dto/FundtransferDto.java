package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class FundtransferDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String fromAccountNumber;
	private String toAccountNumber;
	private Double amount;
	private String comment;

}
