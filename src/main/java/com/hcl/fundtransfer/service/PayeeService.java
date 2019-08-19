package com.hcl.fundtransfer.service;

import java.util.List;

import com.hcl.fundtransfer.dto.PayeeRequestDto;
import com.hcl.fundtransfer.dto.PayeeResponseDto;
import com.hcl.fundtransfer.dto.PayeeUpdateRequestDto;

public interface PayeeService {

	PayeeResponseDto createPayee(PayeeRequestDto request);

	PayeeResponseDto updatePayee(Integer payeeId, PayeeUpdateRequestDto request);

	PayeeResponseDto deletePayee(Integer payeeId, Integer customerId);

	List<PayeeRequestDto> getAllPayees(Integer customerId);

}
