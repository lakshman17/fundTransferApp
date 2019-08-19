package com.hcl.fundtransfer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcl.fundtransfer.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query(value = "select * from transaction t where t.from_account_no=:accuntNumber order by t.creation_date DESC", nativeQuery = true)
	List<Transaction> findByFromAccountNo(@Param("accuntNumber")String accuntNumber, Pageable pageable);

}
