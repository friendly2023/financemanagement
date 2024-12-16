package com.kazimirov.financemanagement.dto;

import com.kazimirov.financemanagement.entity.OrderStatus;

import java.time.LocalDate;

public class OrderResponse {

    private Long id;
    private OrderStatus status;
    private LocalDate dueDate;
    private String timeUtilizationRatio;
    private int totalProductPrice;
    public OrderResponse(Long id, OrderStatus status, LocalDate dueDate, String timeUtilizationRatio, int totalProductPrice) {
        this.id = id;
        this.status = status;
        this.dueDate = dueDate;
        this.timeUtilizationRatio = timeUtilizationRatio;
        this.totalProductPrice = totalProductPrice;
    }

    public Long getId() {
        return id;
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

    public int getTotalProductPrice() {
        return totalProductPrice;
    }
}
