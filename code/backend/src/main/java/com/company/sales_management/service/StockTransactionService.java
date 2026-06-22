package com.company.sales_management.service;

import com.company.sales_management.dto.request.StockTransactionRequest;
import com.company.sales_management.dto.response.StockTransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockTransactionService {
    Page<StockTransactionResponse> findAll(String type, Integer productId, String startDate, String endDate, Pageable pageable);
    StockTransactionResponse create(StockTransactionRequest request);
}
