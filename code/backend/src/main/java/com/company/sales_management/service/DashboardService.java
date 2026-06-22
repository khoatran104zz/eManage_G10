package com.company.sales_management.service;

import com.company.sales_management.dto.response.DashboardResponse;

public interface DashboardService {
    DashboardResponse getDashboardStats(String startDate, String endDate);
}
