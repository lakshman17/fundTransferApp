package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
