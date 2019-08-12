package com.hcl.fundtransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByAccountNumber(Long accountNumber);

}
