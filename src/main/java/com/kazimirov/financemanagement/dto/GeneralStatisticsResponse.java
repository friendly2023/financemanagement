package com.kazimirov.financemanagement.dto;

public class GeneralStatisticsResponse {

    private int orderCount;
    private int orderCompletedCount;
    private int orderOngoingCount;
    private int orderCancelledCount;
    private int orderOverdueCount;
    private int averageOrderPrice;
    private int totalProductSold;
    private int totalEarnings;

    public GeneralStatisticsResponse(int orderCount, int orderCompletedCount, int orderOngoingCount, int orderCancelledCount, int orderOverdueCount, int averageOrderPrice, int totalProductSold, int totalEarnings) {
        this.orderCount = orderCount;
        this.orderCompletedCount = orderCompletedCount;
        this.orderOngoingCount = orderOngoingCount;
        this.orderCancelledCount = orderCancelledCount;
        this.orderOverdueCount = orderOverdueCount;
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

    public int getOrderOngoingCount() {
        return orderOngoingCount;
    }

    public void setOrderOngoingCount(int orderOngoingCount) {
        this.orderOngoingCount = orderOngoingCount;
    }

    public int getOrderCancelledCount() {
        return orderCancelledCount;
    }

    public void setOrderCancelledCount(int orderCancelledCount) {
        this.orderCancelledCount = orderCancelledCount;
    }

    public int getOrderOverdueCount() {
        return orderOverdueCount;
    }

    public void setOrderOverdueCount(int orderOverdueCount) {
        this.orderOverdueCount = orderOverdueCount;
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
