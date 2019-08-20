package com.hcl.fundtransfer.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatementResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer purchaseId;
	private String status;
	private LocalDate purchaseDate;
	private Double price;

}
