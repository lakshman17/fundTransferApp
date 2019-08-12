package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByAccountNumberAndPassword(Long accountNumber, String password);
}
