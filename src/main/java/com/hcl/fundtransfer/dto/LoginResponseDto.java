package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private Integer customerId;
	private String accountNumber;

}
