package com.hcl.fundtransfer.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FundtransferDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long fromAccountNumber;
	private Long toAccountNumber;
	private Double amount;
	private String comment;

}
