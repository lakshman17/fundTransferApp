package com.hcl.fundtransfer.DTO;

import java.io.Serializable;

public class LoginDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long customerId;
	private String password;


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}