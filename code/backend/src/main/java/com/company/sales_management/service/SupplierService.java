package com.company.sales_management.service;

import com.company.sales_management.dto.request.SupplierRequest;
import com.company.sales_management.dto.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    Page<SupplierResponse> findAll(String search, Pageable pageable);
    SupplierResponse findById(Integer id);
    SupplierResponse create(SupplierRequest request);
    SupplierResponse update(Integer id, SupplierRequest request);
    void delete(Integer id);
}
