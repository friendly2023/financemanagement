package com.kazimirov.financemanagement.dto;

import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.model.OrderStatus;

import java.time.LocalDate;

public class OrderDTO {

    private Long id;
    private String title;
    private OrderStatus status;
    private LocalDate dueDate;
    private String timeUtilizationRatio;

    public OrderDTO(Long id, String title, OrderStatus status, LocalDate dueDate, String timeUtilizationRatio) {
        this.id = id;
        this.title = title;
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
}
