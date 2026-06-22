package com.company.sales_management.service;

import com.company.sales_management.dto.request.SettingRequest;
import com.company.sales_management.dto.response.SettingResponse;

public interface SettingService {
    SettingResponse findSettings();
    SettingResponse updateSettings(SettingRequest request);
}
