package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long customerId;
	private String firstName;
	private String lastName;
	private String password;
	private Long mobileNumber;
	private Long accountNumber;
}
