package com.kazimirov.financemanagement.dto;

import jakarta.persistence.Column;

public class ProductResponse {

    private Long id;

    private String productName;

    private double price;

    private String note;

    public ProductResponse(Long id, String productName, double price, String note) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
