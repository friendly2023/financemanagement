package com.kazimirov.financemanagement.entity;


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "city")
    private String city;

    @Column(name = "total_order_amount", nullable = false)
    private int totalOrderAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    //TODO перенести это в service
    @PrePersist
    public void validate() {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalStateException("Отсутствует заголовок заказа");
        }
        if (dueDate == null) {
            throw new IllegalStateException("Отсутствует дедлайн заказа");
        }
    }

    public OrderEntity() {
        this.orderDate = LocalDate.now();
    }

    public OrderEntity(String title, String note, LocalDate orderDate, LocalDate dueDate, OrderStatus status) {
        this.title = title;
        this.note = note;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.status = status;
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

    public String getNote() {
        return note;
    }

    public void setNote(String description) {
        this.note = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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

    public ClientEntity getClient() {
        return clientEntity;
    }

    public void setClient(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

}
