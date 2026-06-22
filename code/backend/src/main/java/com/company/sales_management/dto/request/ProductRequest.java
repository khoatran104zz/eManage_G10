package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;

public class ProductRequest {

    @NotBlank(message = "SKU là bắt buộc")
    @Size(max = 50, message = "SKU không được vượt quá 50 ký tự")
    private String sku;

    @NotBlank(message = "Tên sản phẩm là bắt buộc")
    @Size(max = 255, message = "Tên sản phẩm không được vượt quá 255 ký tự")
    private String name;

    private Integer categoryId;

    private Integer brandId;

    @Min(value = 0, message = "Giá nhập không được âm")
    private Double costPrice = 0.0;

    @Min(value = 0, message = "Giá bán không được âm")
    private Double salePrice = 0.0;

    @Min(value = 0, message = "Số lượng tồn không được âm")
    private Integer stock = 0;

    private String image;

    private String description;

    // Getters and Setters
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public Integer getBrandId() { return brandId; }
    public void setBrandId(Integer brandId) { this.brandId = brandId; }
    public Double getCostPrice() { return costPrice; }
    public void setCostPrice(Double costPrice) { this.costPrice = costPrice; }
    public Double getSalePrice() { return salePrice; }
    public void setSalePrice(Double salePrice) { this.salePrice = salePrice; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
