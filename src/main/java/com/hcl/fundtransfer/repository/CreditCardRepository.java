/**
 * 
 */
package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.CardDetails;

/**
 * @author user1
 *
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CardDetails, Integer> {

}
