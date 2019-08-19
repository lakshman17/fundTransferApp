package com.hcl.fundtransfer.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class TransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer transactionId;
	private String fromAccountNo;
	private String toAccountNo;
	private Double amount;
	private LocalDate creationDate;
	private String transactionType;
	private Double closingBalance;
	private String comment;

}
