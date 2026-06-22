package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;
import java.util.List;

public class OrderRequest {

    private Integer customerId;

    private String employeeId;

    private Integer branchId;

    @NotNull(message = "Tổng tiền là bắt buộc")
    @Min(value = 0, message = "Tổng tiền không được âm")
    private Double total;

    @Min(value = 0, message = "Tiền giảm giá không được âm")
    private Double discount = 0.0;

    @NotBlank(message = "Phương thức thanh toán là bắt buộc")
    private String paymentMethod;

    private String status;

    private String note;

    @NotEmpty(message = "Đơn hàng phải có ít nhất 1 sản phẩm")
    private List<OrderItemRequest> items;

    // Getters and Setters
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public Integer getBranchId() { return branchId; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
