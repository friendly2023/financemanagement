package com.kazimirov.financemanagement.dto;

public class StatisticsOnDatesResponse {
    private int year;
    private String month;
    private int totalOrdersSold;
    private int totalEarnings;

    public StatisticsOnDatesResponse(int year, String month, int totalOrdersSold, int totalEarnings) {
        this.year = year;
        this.month = month;
        this.totalOrdersSold = totalOrdersSold;
        this.totalEarnings = totalEarnings;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotalOrdersSold() {
        return totalOrdersSold;
    }

    public void setTotalOrdersSold(int totalOrdersSold) {
        this.totalOrdersSold = totalOrdersSold;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
}
