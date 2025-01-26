package com.kazimirov.financemanagement.dto;

public class YearSummary {
    private int totalOrders;
    private double totalEarnings;

    public YearSummary(int totalOrders, double totalEarnings) {
        this.totalOrders = totalOrders;
        this.totalEarnings = totalEarnings;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }
}
