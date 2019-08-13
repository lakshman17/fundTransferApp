package com.hcl.fundtransfer.service;

import com.hcl.fundtransfer.dto.CustomerDTO;
import com.hcl.fundtransfer.dto.CustomerResponseDTO;

public interface CustomerService {

	CustomerResponseDTO createCustomer(CustomerDTO customerDTO);

}
