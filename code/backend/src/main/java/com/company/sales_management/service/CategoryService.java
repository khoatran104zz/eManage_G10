package com.company.sales_management.service;

import com.company.sales_management.dto.request.CategoryRequest;
import com.company.sales_management.dto.response.CategoryResponse;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll(String search);
    CategoryResponse findById(Integer id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Integer id, CategoryRequest request);
    void delete(Integer id);
}
