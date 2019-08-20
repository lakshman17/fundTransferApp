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
public class CardValidationResponseDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer cardId;

}
