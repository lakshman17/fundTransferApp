package com.hcl.fundtransfer.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long transactionId;
	private Long fromAccountNo;
	private Long toAccountNo;
	private Double amount;
	private LocalDate creationDate;
	private String transactionType;
	private Double closingBalance;
	private String comment;

}
