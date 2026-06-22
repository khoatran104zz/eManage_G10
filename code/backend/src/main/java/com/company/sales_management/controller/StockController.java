package com.company.sales_management.controller;

import com.company.sales_management.dto.request.StockTransactionRequest;
import com.company.sales_management.dto.response.StockTransactionResponse;
import com.company.sales_management.dto.response.ProductResponse;
import com.company.sales_management.service.ProductService;
import com.company.sales_management.service.StockTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin
public class StockController {

    @Autowired
    private StockTransactionService stockTransactionService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        // Return flat list of all active products (which contains the current stock level)
        Page<ProductResponse> productsPage = productService.findAll(null, null, null, true, PageRequest.of(0, 10000));
        return ResponseEntity.ok(productsPage.getContent());
    }

    @GetMapping("/history")
    public ResponseEntity<List<StockTransactionResponse>> getHistory(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        String transactionType = null;
        if (type != null && !type.isBlank()) {
            transactionType = type.trim().toUpperCase();
        }

        Page<StockTransactionResponse> page = stockTransactionService.findAll(
                transactionType, productId, startDate, endDate, PageRequest.of(0, 10000));
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping("/import")
    public ResponseEntity<Map<String, Object>> importStock(@Valid @RequestBody StockTransactionRequest request) {
        request.setType("IMPORT");
        StockTransactionResponse transactionResponse = stockTransactionService.create(request);

        // Fetch updated stock level for response mapping
        ProductResponse product = productService.findById(request.getProductId());

        Map<String, Object> body = new HashMap<>();
        body.put("transaction", transactionResponse);
        body.put("newStock", product.getStock());

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PostMapping("/export")
    public ResponseEntity<Map<String, Object>> exportStock(@Valid @RequestBody StockTransactionRequest request) {
        request.setType("EXPORT");
        StockTransactionResponse transactionResponse = stockTransactionService.create(request);

        // Fetch updated stock level for response mapping
        ProductResponse product = productService.findById(request.getProductId());

        Map<String, Object> body = new HashMap<>();
        body.put("transaction", transactionResponse);
        body.put("newStock", product.getStock());

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
