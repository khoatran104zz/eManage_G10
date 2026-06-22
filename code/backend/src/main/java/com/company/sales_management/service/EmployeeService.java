package com.company.sales_management.service;

import com.company.sales_management.dto.request.EmployeeRequest;
import com.company.sales_management.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<EmployeeResponse> findAll(String search, Integer branchId, Pageable pageable);
    EmployeeResponse findById(Integer id);
    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse update(Integer id, EmployeeRequest request);
    void delete(Integer id);
}
