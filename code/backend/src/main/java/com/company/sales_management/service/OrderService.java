package com.company.sales_management.service;

import com.company.sales_management.dto.request.OrderRequest;
import com.company.sales_management.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderResponse> findAll(String search, String status, String startDate, String endDate, Pageable pageable);
    OrderResponse findById(Integer id);
    OrderResponse create(OrderRequest request);
    OrderResponse updateStatus(Integer id, String status);
}
