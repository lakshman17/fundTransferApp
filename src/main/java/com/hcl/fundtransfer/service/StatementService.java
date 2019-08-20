package com.hcl.fundtransfer.service;

import java.util.List;

import com.hcl.fundtransfer.dto.StatementRequestDto;
import com.hcl.fundtransfer.dto.StatementResponseDto;

public interface StatementService {

	//List<StatementResponseDto> getAllStatements(Integer cardId);

	List<StatementResponseDto> getAllStatements(StatementRequestDto statementRequestDto);

}
