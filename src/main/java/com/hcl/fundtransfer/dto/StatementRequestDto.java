package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatementRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cardId;
	private String purchaseDate;

}
