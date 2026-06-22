package com.company.sales_management.service.impl;

import com.company.sales_management.dto.request.SupplierRequest;
import com.company.sales_management.dto.response.SupplierResponse;
import com.company.sales_management.entity.Supplier;
import com.company.sales_management.exception.ResourceNotFoundException;
import com.company.sales_management.repository.SupplierRepository;
import com.company.sales_management.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Page<SupplierResponse> findAll(String search, Pageable pageable) {
        if (search != null && !search.isBlank()) {
            return supplierRepository.findBySearchTerm(search, pageable).map(this::toResponse);
        }
        return supplierRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public SupplierResponse findById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nhà cung cấp", "id", id));
        return toResponse(supplier);
    }

    @Override
    public SupplierResponse create(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setContactPerson(request.getContactPerson());
        return toResponse(supplierRepository.save(supplier));
    }

    @Override
    public SupplierResponse update(Integer id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nhà cung cấp", "id", id));
        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setContactPerson(request.getContactPerson());
        return toResponse(supplierRepository.save(supplier));
    }

    @Override
    public void delete(Integer id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nhà cung cấp", "id", id);
        }
        supplierRepository.deleteById(id);
    }

    private SupplierResponse toResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setPhone(supplier.getPhone());
        response.setEmail(supplier.getEmail());
        response.setAddress(supplier.getAddress());
        response.setContactPerson(supplier.getContactPerson());
        response.setCreatedAt(supplier.getCreatedAt());
        response.setUpdatedAt(supplier.getUpdatedAt());
        return response;
    }
}
