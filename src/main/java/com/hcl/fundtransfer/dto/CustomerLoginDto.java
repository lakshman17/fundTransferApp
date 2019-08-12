package com.hcl.fundtransfer.dto;

import java.io.Serializable;

public class CustomerLoginDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long accountNumber;
	private String password;
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
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
