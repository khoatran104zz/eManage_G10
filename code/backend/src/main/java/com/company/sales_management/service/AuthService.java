package com.company.sales_management.service;

import com.company.sales_management.dto.request.LoginRequest;
import com.company.sales_management.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
