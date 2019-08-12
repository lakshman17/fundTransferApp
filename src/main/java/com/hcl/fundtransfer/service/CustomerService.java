package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.DTO.CustomerDTO;
import com.hcl.fundtransfer.DTO.CustomerResponseDTO;

public interface CustomerService {

	CustomerResponseDTO createCustomer(CustomerDTO customerDTO);

}
