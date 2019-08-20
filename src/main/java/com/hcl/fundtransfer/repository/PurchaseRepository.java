/**
 * 
 */
package com.hcl.fundtransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Purchase;

/**
 * @author user1
 *
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
	
	Optional<Purchase> findByPrice(double price);

}
