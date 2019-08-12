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
public class CustomerResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long accountNumber;
	private String message;
}
