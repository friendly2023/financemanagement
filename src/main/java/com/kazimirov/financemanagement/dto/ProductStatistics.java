package com.kazimirov.financemanagement.dto;

public class ProductStatistics {
    private String productName;
    private int quantitySold;
    private int totalPrice;
    private double percentageOfTotalEarnings;

    public ProductStatistics(String productName, int quantitySold, int totalPrice, double percentageOfTotalEarnings) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
        this.percentageOfTotalEarnings = percentageOfTotalEarnings;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPercentageOfTotalEarnings() {
        return percentageOfTotalEarnings;
    }

    public void setPercentageOfTotalEarnings(double percentageOfTotalEarnings) {
        this.percentageOfTotalEarnings = percentageOfTotalEarnings;
    }
}
