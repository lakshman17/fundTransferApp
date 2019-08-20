package com.hcl.fundtransfer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	@Query("select p from Purchase p where p.cardId=:cardId")
	List<Purchase> findAllById(@Param("cardId") Integer cardId);

	Optional<Purchase> findByPrice(Double price);

}
