package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
