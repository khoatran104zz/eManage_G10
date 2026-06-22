package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class StockTransactionRequest {

    @NotNull(message = "Sản phẩm là bắt buộc")
    private Integer productId;

    @Size(max = 20, message = "Loại giao dịch không được vượt quá 20 ký tự")
    private String type; // IMPORT, EXPORT, ADJUST

    @NotNull(message = "Số lượng là bắt buộc")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity;

    @DecimalMin(value = "0.0", message = "Đơn giá không được âm")
    private BigDecimal unitPrice;

    private Integer supplierId;

    private String note;

    private Integer branchId;

    // Getters and Setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Integer getBranchId() { return branchId; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
}
