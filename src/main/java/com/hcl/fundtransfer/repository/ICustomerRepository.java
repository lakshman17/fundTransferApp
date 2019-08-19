package com.hcl.fundtransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByAccountNumberAndPassword(String accountNumber, String password);
	
	public Optional<Customer> findByCustomerIdNotIn(String accountNumber);
	
	public Optional<Customer> findByAccountNumber(String accountNumber);
}
