package com.company.sales_management.dto.response;

import java.util.List;
import java.math.BigDecimal;

public class DashboardResponse {
    private Long totalOrders;
    private BigDecimal totalRevenue;
    private Long totalProducts;
    private Long totalCustomers;
    private Long lowStockProducts;
    private List<RevenueByDayResponse> revenueByDay;
    private List<TopProductResponse> topProducts;

    public Long getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Long totalOrders) { this.totalOrders = totalOrders; }
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    public Long getTotalProducts() { return totalProducts; }
    public void setTotalProducts(Long totalProducts) { this.totalProducts = totalProducts; }
    public Long getTotalCustomers() { return totalCustomers; }
    public void setTotalCustomers(Long totalCustomers) { this.totalCustomers = totalCustomers; }
    public Long getLowStockProducts() { return lowStockProducts; }
    public void setLowStockProducts(Long lowStockProducts) { this.lowStockProducts = lowStockProducts; }
    public List<RevenueByDayResponse> getRevenueByDay() { return revenueByDay; }
    public void setRevenueByDay(List<RevenueByDayResponse> revenueByDay) { this.revenueByDay = revenueByDay; }
    public List<TopProductResponse> getTopProducts() { return topProducts; }
    public void setTopProducts(List<TopProductResponse> topProducts) { this.topProducts = topProducts; }

    // Inner classes
    public static class RevenueByDayResponse {
        private String date;
        private BigDecimal revenue;
        private Long orders;

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public BigDecimal getRevenue() { return revenue; }
        public void setRevenue(BigDecimal revenue) { this.revenue = revenue; }
        public Long getOrders() { return orders; }
        public void setOrders(Long orders) { this.orders = orders; }
    }

    public static class TopProductResponse {
        private Integer productId;
        private String productName;
        private Long totalQuantity;
        private java.math.BigDecimal totalRevenue;

        public Integer getProductId() { return productId; }
        public void setProductId(Integer productId) { this.productId = productId; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public Long getTotalQuantity() { return totalQuantity; }
        public void setTotalQuantity(Long totalQuantity) { this.totalQuantity = totalQuantity; }
        public java.math.BigDecimal getTotalRevenue() { return totalRevenue; }
        public void setTotalRevenue(java.math.BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    }
}
