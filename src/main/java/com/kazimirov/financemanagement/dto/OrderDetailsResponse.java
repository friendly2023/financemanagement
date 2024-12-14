package com.kazimirov.financemanagement.dto;

import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.entity.OrderStatus;

import java.time.LocalDate;

public class OrderDetailsResponse {

    private Long id;
    private String note;
    private OrderStatus status;
    private LocalDate dueDate;
    private LocalDate orderDate;
    private String city;
    private String timeUtilizationRatio;
    private int totalProductPrice;
    private String compositionOfOrder;
    private ClientEntity clientEntity;

    public OrderDetailsResponse(Long id,
                                String note,
                                OrderStatus status,
                                LocalDate dueDate,
                                LocalDate orderDate,
                                String city,
                                String timeUtilizationRatio,
                                int totalProductPrice,
                                String compositionOfOrder,
                                ClientEntity clientEntity) {
        this.id = id;
        this.note = note;
        this.status = status;
        this.dueDate = dueDate;
        this.orderDate = orderDate;
        this.city = city;
        this.timeUtilizationRatio = timeUtilizationRatio;
        this.totalProductPrice = totalProductPrice;
        this.compositionOfOrder = compositionOfOrder;
        this.clientEntity = clientEntity;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getTimeUtilizationRatio() {
        return timeUtilizationRatio;
    }


    public int getTotalProductPrice() {
        return totalProductPrice;
    }


    public String getCompositionOfOrder() {
        return compositionOfOrder;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
