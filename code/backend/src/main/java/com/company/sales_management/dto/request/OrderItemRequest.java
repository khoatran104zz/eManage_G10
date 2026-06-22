package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;

public class OrderItemRequest {

    @NotNull(message = "Sản phẩm là bắt buộc")
    private Integer productId;

    @NotBlank(message = "Tên sản phẩm là bắt buộc")
    private String productName;

    @NotNull(message = "Số lượng là bắt buộc")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity;

    @NotNull(message = "Đơn giá là bắt buộc")
    @Min(value = 0, message = "Đơn giá không được âm")
    private Double price;

    // Getters and Setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
