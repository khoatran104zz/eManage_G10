package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;

public class EmployeeRequest {

    @NotBlank(message = "Tên nhân viên là bắt buộc")
    @Size(max = 100, message = "Tên nhân viên không được vượt quá 100 ký tự")
    private String name;

    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    private String phone;

    @Email(message = "Email không hợp lệ")
    @Size(max = 150, message = "Email không được vượt quá 150 ký tự")
    private String email;

    @Size(max = 100, message = "Vị trí không được vượt quá 100 ký tự")
    private String position;

    private Integer branchId;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Integer getBranchId() { return branchId; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
}
