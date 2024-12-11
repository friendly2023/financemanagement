package com.kazimirov.financemanagement.dto;

import com.kazimirov.financemanagement.entity.OrderStatus;

import java.time.LocalDate;

public class OrderResponse {

    private Long id;
    private String title;
    private OrderStatus status;
    private LocalDate dueDate;
    private String timeUtilizationRatio;
    private int totalOrderAmount;

    public OrderResponse(Long id, String title, int totalOrderAmount, OrderStatus status, LocalDate dueDate, String timeUtilizationRatio) {
        this.id = id;
        this.title = title;
        this.totalOrderAmount = totalOrderAmount;
        this.status = status;
        this.dueDate = dueDate;
        this.timeUtilizationRatio = timeUtilizationRatio;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTimeUtilizationRatio() {
        return timeUtilizationRatio;
    }

    public void setTimeUtilizationRatio(String timeUtilizationRatio) {
        this.timeUtilizationRatio = timeUtilizationRatio;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
}
