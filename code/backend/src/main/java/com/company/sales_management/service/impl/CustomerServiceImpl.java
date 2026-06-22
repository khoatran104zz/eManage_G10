package com.company.sales_management.service.impl;

import com.company.sales_management.dto.request.CustomerRequest;
import com.company.sales_management.dto.response.CustomerResponse;
import com.company.sales_management.entity.Customer;
import com.company.sales_management.exception.ResourceNotFoundException;
import com.company.sales_management.repository.CustomerRepository;
import com.company.sales_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<CustomerResponse> findAll(String search, Pageable pageable) {
        if (search != null && !search.isBlank()) {
            return customerRepository.findBySearchTerm(search, pageable).map(this::toResponse);
        }
        return customerRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public CustomerResponse findById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng", "id", id));
        return toResponse(customer);
    }

    @Override
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setCode("KH" + System.currentTimeMillis());
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setPoints(0);
        return toResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse update(Integer id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng", "id", id));
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        return toResponse(customerRepository.save(customer));
    }

    @Override
    public void delete(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Khách hàng", "id", id);
        }
        customerRepository.deleteById(id);
    }

    private CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setCode(customer.getCode());
        response.setName(customer.getName());
        response.setPhone(customer.getPhone());
        response.setEmail(customer.getEmail());
        response.setAddress(customer.getAddress());
        response.setPoints(customer.getPoints());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }
}
