package com.company.sales_management.service.impl;

import com.company.sales_management.dto.request.BrandRequest;
import com.company.sales_management.dto.response.BrandResponse;
import com.company.sales_management.entity.Brand;
import com.company.sales_management.exception.BadRequestException;
import com.company.sales_management.exception.ResourceNotFoundException;
import com.company.sales_management.repository.BrandRepository;
import com.company.sales_management.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<BrandResponse> findAll(String search) {
        List<Brand> brands;
        if (search != null && !search.isBlank()) {
            brands = brandRepository.findByNameContainingIgnoreCase(search);
        } else {
            brands = brandRepository.findAll();
        }
        return brands.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public BrandResponse findById(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thương hiệu", "id", id));
        return toResponse(brand);
    }

    @Override
    public BrandResponse create(BrandRequest request) {
        if (brandRepository.existsByName(request.getName())) {
            throw new BadRequestException("Tên thương hiệu đã tồn tại: " + request.getName());
        }
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return toResponse(brandRepository.save(brand));
    }

    @Override
    public BrandResponse update(Integer id, BrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thương hiệu", "id", id));
        if (!brand.getName().equals(request.getName()) && brandRepository.existsByName(request.getName())) {
            throw new BadRequestException("Tên thương hiệu đã tồn tại: " + request.getName());
        }
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return toResponse(brandRepository.save(brand));
    }

    @Override
    public void delete(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Thương hiệu", "id", id);
        }
        brandRepository.deleteById(id);
    }

    private BrandResponse toResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setDescription(brand.getDescription());
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());
        return response;
    }
}
