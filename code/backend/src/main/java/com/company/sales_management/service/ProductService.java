package com.company.sales_management.service;

import com.company.sales_management.dto.request.ProductRequest;
import com.company.sales_management.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Page<ProductResponse> findAll(String search, Integer categoryId, Integer brandId, Boolean active, Pageable pageable);
    List<ProductResponse> findLowStock();
    ProductResponse findById(Integer id);
    ProductResponse create(ProductRequest request);
    ProductResponse update(Integer id, ProductRequest request);
    void delete(Integer id);
}
