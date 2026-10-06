package com.salesdashboard.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Sale {

    private int saleId;
    private LocalDate saleDate;
    private String product;
    private String category;
    private String region;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal unitCost;
    private String customerType;
    private String paymentMethod;

    public Sale() {
    }

    public Sale(
            int saleId,
            LocalDate saleDate,
            String product,
            String category,
            String region,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal unitCost,
            String customerType,
            String paymentMethod) {

        this.saleId = saleId;
        this.saleDate = saleDate;
        this.product = product;
        this.category = category;
        this.region = region;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.customerType = customerType;
        this.paymentMethod = paymentMethod;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getSalesAmount() {
        return unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
    }

    public BigDecimal getProfit() {
        return unitPrice
                .subtract(unitCost)
                .multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {

        return saleId +
                " | " + saleDate +
                " | " + product +
                " | " + category +
                " | " + region +
                " | Qty: " + quantity +
                " | Sales: ₹" + getSalesAmount() +
                " | Profit: ₹" + getProfit();
    }
}
