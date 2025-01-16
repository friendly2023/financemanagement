package com.kazimirov.financemanagement.dto;

import com.kazimirov.financemanagement.enums.OrderStatus;

import java.time.LocalDate;

public class OrderResponse {

    private Long id;
    private OrderStatus status;
    private LocalDate dueDate;
    private String timeUtilizationRatio;
    private int totalProductPrice;
    private String compositionOfOrder;

    public OrderResponse() {
    }

    public OrderResponse(Long id, OrderStatus status, LocalDate dueDate, String timeUtilizationRatio, int totalProductPrice, String compositionOfOrder) {
        this.id = id;
        this.status = status;
        this.dueDate = dueDate;
        this.timeUtilizationRatio = timeUtilizationRatio;
        this.totalProductPrice = totalProductPrice;
        this.compositionOfOrder = compositionOfOrder;
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

    public String getCompositionOfOrder() {
        return compositionOfOrder;
    }

    public void setCompositionOfOrder(String compositionOfOrder) {
        this.compositionOfOrder = compositionOfOrder;
    }
}
