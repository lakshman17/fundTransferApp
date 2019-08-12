package com.hcl.fundtransfer.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	private Long fromAccountNo;
	private Long toAccountNo;
	private Double amount;
	private LocalDate creationDate;
	private String transactionType;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}