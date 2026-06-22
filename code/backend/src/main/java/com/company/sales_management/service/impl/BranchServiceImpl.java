package com.company.sales_management.service.impl;

import com.company.sales_management.dto.request.BranchRequest;
import com.company.sales_management.dto.response.BranchResponse;
import com.company.sales_management.entity.Branch;
import com.company.sales_management.exception.ResourceNotFoundException;
import com.company.sales_management.repository.BranchRepository;
import com.company.sales_management.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchResponse> findAll() {
        return branchRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public BranchResponse findById(Integer id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chi nhánh", "id", id));
        return toResponse(branch);
    }

    @Override
    public BranchResponse create(BranchRequest request) {
        Branch branch = new Branch();
        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        branch.setPhone(request.getPhone());
        return toResponse(branchRepository.save(branch));
    }

    @Override
    public BranchResponse update(Integer id, BranchRequest request) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chi nhánh", "id", id));
        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        branch.setPhone(request.getPhone());
        return toResponse(branchRepository.save(branch));
    }

    @Override
    public void delete(Integer id) {
        if (!branchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chi nhánh", "id", id);
        }
        branchRepository.deleteById(id);
    }

    private BranchResponse toResponse(Branch branch) {
        BranchResponse response = new BranchResponse();
        response.setId(branch.getId());
        response.setName(branch.getName());
        response.setAddress(branch.getAddress());
        response.setPhone(branch.getPhone());
        response.setCreatedAt(branch.getCreatedAt());
        response.setUpdatedAt(branch.getUpdatedAt());
        return response;
    }
}
