package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;

public class CustomerRequest {

    @NotBlank(message = "Tên khách hàng là bắt buộc")
    @Size(max = 150, message = "Tên khách hàng không được vượt quá 150 ký tự")
    private String name;

    @NotBlank(message = "Số điện thoại là bắt buộc")
    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    private String phone;

    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    private String email;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    private String address;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
