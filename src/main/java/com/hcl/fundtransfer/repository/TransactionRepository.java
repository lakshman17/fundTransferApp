package com.hcl.fundtransfer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByFromAccountNo(Long accuntNumber,Pageable pageable);

}
