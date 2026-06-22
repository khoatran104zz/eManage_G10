package com.company.sales_management.service;

import com.company.sales_management.dto.request.BranchRequest;
import com.company.sales_management.dto.response.BranchResponse;
import java.util.List;

public interface BranchService {
    List<BranchResponse> findAll();
    BranchResponse findById(Integer id);
    BranchResponse create(BranchRequest request);
    BranchResponse update(Integer id, BranchRequest request);
    void delete(Integer id);
}
