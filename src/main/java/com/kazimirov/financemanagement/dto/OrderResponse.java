package com.kazimirov.financemanagement.dto;

import com.kazimirov.financemanagement.enums.OrderStatus;

import java.time.LocalDate;

public class OrderResponse {

    private Long id;
    private OrderStatus status;
    private LocalDate orderDate;
    private String timeUtilizationRatio;
    private int totalProductPrice;
    private String compositionOfOrder;

    public OrderResponse() {
    }

    public OrderResponse(Long id, OrderStatus status, LocalDate orderDate, String timeUtilizationRatio, int totalProductPrice, String compositionOfOrder) {
        this.id = id;
        this.status = status;
        this.orderDate = orderDate;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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
