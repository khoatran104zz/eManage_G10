package com.company.sales_management.service;

import com.company.sales_management.dto.request.CustomerRequest;
import com.company.sales_management.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerResponse> findAll(String search, Pageable pageable);
    CustomerResponse findById(Integer id);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Integer id, CustomerRequest request);
    void delete(Integer id);
}
