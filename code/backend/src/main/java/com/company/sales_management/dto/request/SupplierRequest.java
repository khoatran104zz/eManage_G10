package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;

public class SupplierRequest {

    @NotBlank(message = "Tên nhà cung cấp là bắt buộc")
    @Size(max = 150, message = "Tên nhà cung cấp không được vượt quá 150 ký tự")
    private String name;

    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    private String phone;

    @Email(message = "Email không hợp lệ")
    @Size(max = 150, message = "Email không được vượt quá 150 ký tự")
    private String email;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    private String address;

    @Size(max = 150, message = "Người liên hệ không được vượt quá 150 ký tự")
    private String contactPerson;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
}
