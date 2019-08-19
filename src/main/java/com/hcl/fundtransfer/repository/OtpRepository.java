package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer>{

}
