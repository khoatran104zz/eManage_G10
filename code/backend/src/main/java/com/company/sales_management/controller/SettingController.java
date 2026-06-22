package com.company.sales_management.controller;

import com.company.sales_management.dto.request.SettingRequest;
import com.company.sales_management.dto.response.SettingResponse;
import com.company.sales_management.service.SettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping
    public ResponseEntity<SettingResponse> getSettings() {
        return ResponseEntity.ok(settingService.findSettings());
    }

    @PutMapping
    public ResponseEntity<SettingResponse> updateSettings(@Valid @RequestBody SettingRequest request) {
        return ResponseEntity.ok(settingService.updateSettings(request));
    }
}
