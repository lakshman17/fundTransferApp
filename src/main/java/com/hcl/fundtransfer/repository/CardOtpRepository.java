package com.hcl.fundtransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcl.fundtransfer.entity.CreditOtp;
import com.hcl.fundtransfer.entity.Otp;

public interface CardOtpRepository extends JpaRepository<CreditOtp, Integer> {

//	@Query("select p from Otp p where p.payee.payeeId=:payeeId")
//	Optional<Otp> getPayeeOtpNumber(@Param("payeeId") Integer payeeId);

}
