package com.company.sales_management.service;

import com.company.sales_management.dto.request.UserRequest;
import com.company.sales_management.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> findAll(String search, Pageable pageable);
    UserResponse findById(Integer id);
    UserResponse create(UserRequest request);
    UserResponse update(Integer id, UserRequest request);
    void delete(Integer id);
}
