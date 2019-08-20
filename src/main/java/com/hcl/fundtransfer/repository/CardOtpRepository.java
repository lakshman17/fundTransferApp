package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.CreditOtp;

public interface CardOtpRepository extends JpaRepository<CreditOtp, Integer> {


}
