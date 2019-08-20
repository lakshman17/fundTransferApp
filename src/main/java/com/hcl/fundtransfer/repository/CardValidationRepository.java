package com.hcl.fundtransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.CardDetails;

@Repository
public interface CardValidationRepository extends JpaRepository<CardDetails, Integer> {
	
	Optional<CardDetails> findByCardNumber(Long cardNumber);

}
