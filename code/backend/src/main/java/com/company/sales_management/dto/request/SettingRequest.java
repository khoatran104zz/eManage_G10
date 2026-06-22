package com.company.sales_management.dto.request;

import jakarta.validation.constraints.*;

public class SettingRequest {

    @Size(max = 150, message = "Tên cửa hàng không được vượt quá 150 ký tự")
    private String shopName;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    private String address;

    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    private String phone;

    @Email(message = "Email không hợp lệ")
    @Size(max = 150, message = "Email không được vượt quá 150 ký tự")
    private String email;

    @Size(max = 50, message = "Đơn vị tiền tệ không được vượt quá 50 ký tự")
    private String currency;

    @Size(max = 255, message = "Logo URL không được vượt quá 255 ký tự")
    private String logoUrl;

    private String taxRate;
    private String loyaltyPointRate;

    // Getters and Setters
    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getTaxRate() { return taxRate; }
    public void setTaxRate(String taxRate) { this.taxRate = taxRate; }
    public String getLoyaltyPointRate() { return loyaltyPointRate; }
    public void setLoyaltyPointRate(String loyaltyPointRate) { this.loyaltyPointRate = loyaltyPointRate; }
}
