package com.kazimirov.financemanagement.dto;

public class GeneralStatisticsResponse {

    private int orderCount;
    private int orderCompletedCount;
    private int averageOrderPrice;
    private int totalProductSold;
    private int totalEarnings;

    public GeneralStatisticsResponse(int orderCount, int orderCompletedCount, int averageOrderPrice, int totalProductSold, int totalEarnings) {
        this.orderCount = orderCount;
        this.orderCompletedCount = orderCompletedCount;
        this.averageOrderPrice = averageOrderPrice;
        this.totalProductSold = totalProductSold;
        this.totalEarnings = totalEarnings;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getOrderCompletedCount() {
        return orderCompletedCount;
    }

    public void setOrderCompletedCount(int orderCompletedCount) {
        this.orderCompletedCount = orderCompletedCount;
    }

    public int getAverageOrderPrice() {
        return averageOrderPrice;
    }

    public void setAverageOrderPrice(int averageOrderPrice) {
        this.averageOrderPrice = averageOrderPrice;
    }

    public int getTotalProductSold() {
        return totalProductSold;
    }

    public void setTotalProductSold(int totalProductSold) {
        this.totalProductSold = totalProductSold;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
}
