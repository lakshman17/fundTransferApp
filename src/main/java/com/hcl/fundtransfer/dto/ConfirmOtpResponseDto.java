/**
 * 
 */
package com.hcl.fundtransfer.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author user1
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmOtpResponseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

}
