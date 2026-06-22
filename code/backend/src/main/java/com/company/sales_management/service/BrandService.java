package com.company.sales_management.service;

import com.company.sales_management.dto.request.BrandRequest;
import com.company.sales_management.dto.response.BrandResponse;
import java.util.List;

public interface BrandService {
    List<BrandResponse> findAll(String search);
    BrandResponse findById(Integer id);
    BrandResponse create(BrandRequest request);
    BrandResponse update(Integer id, BrandRequest request);
    void delete(Integer id);
}
