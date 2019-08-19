package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CustomerLoginDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String accountNumber;
	private String password;

}
