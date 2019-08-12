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
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long customerId;
	private String firstName;
	private String lastName;
	private String password;
	private Long mobileNumber;
	private Long accountNumber;
}
