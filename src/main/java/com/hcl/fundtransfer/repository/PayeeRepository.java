package com.hcl.fundtransfer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Integer> {

	Optional<Payee> findByPayeeAccountNumberAndStatus(String payeeAccountNumber, String string);

	@Query("select p from Payee p where p.customer.customerId=:customerId")
	List<Payee> findAllById(@Param("customerId") Integer customerId);

	Optional<Payee> findByPayeeAccountNumber(String accountNumber);

}
