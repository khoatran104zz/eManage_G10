package com.company.sales_management.controller;

import com.company.sales_management.dto.request.OrderRequest;
import com.company.sales_management.dto.response.OrderResponse;
import com.company.sales_management.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Integer size) {

        int pageSize = size != null ? size : limit;
        int pageIndex = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<OrderResponse> orderPage = orderService.findAll(search, status, startDate, endDate, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", orderPage.getContent());
        response.put("totalPages", orderPage.getTotalPages());
        response.put("total", orderPage.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        if (status == null) {
            throw new IllegalArgumentException("Trạng thái là bắt buộc");
        }
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
