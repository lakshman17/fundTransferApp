package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Long>{

}
