package com.hcl.fundtransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
	
	Optional<Account> findByAccountNumber(String accountNumber);
}
