package com.hcl.fundtransfer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.StatementResponseDto;
import com.hcl.fundtransfer.entity.Purchase;
import com.hcl.fundtransfer.exception.StatementException;
import com.hcl.fundtransfer.repository.PurchaseRepository;

@Service
public class StatementServiceImpl implements StatementService {
	public static final Logger LOGGER = LoggerFactory.getLogger(StatementServiceImpl.class);
	@Autowired
	PurchaseRepository purchaseRepository;

	@Override
	public List<StatementResponseDto> getAllStatements(Integer cardId, String purchaseDate) {

		List<Purchase> purchaseList = purchaseRepository.findAllById(cardId);
		if (purchaseList.isEmpty()) {
			throw new StatementException(cardId);
		} else {
			List<StatementResponseDto> purchaseListDto = new ArrayList<>();

			purchaseList.stream().forEach(s -> {
				StatementResponseDto statementResponseDto = new StatementResponseDto();
				String splitString = s.getPurchaseDate().toString().substring(0, 7);
				if (splitString.equalsIgnoreCase(purchaseDate)) {
					BeanUtils.copyProperties(s, statementResponseDto);
					purchaseListDto.add(statementResponseDto);
				}

			});
			LOGGER.info("Monthly statements fetched");
			return purchaseListDto;
		}
	}
}

//	@Override
//	public List<StatementResponseDto> getAllStatements(Integer cardId) {
//		
//		List<StatementResponseDto> responseList = new ArrayList<>();
//		List<Purchase> purchaseList = purchaseRepository.findAllById(cardId);
//		if (purchaseList.isEmpty()) {
//			throw new StatementException(cardId);
//		} else {
//			purchaseList.stream().forEach(S -> {
//				StatementResponseDto statementResponseDto = new StatementResponseDto();
//				
//				BeanUtils.copyProperties(S, statementResponseDto);
//				responseList.add(statementResponseDto);
//			});
//
//		}
//		return responseList;
//	}
