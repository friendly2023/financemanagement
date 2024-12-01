package com.kazimirov.financemanagement.model;


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

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

    public Order() {
        this.orderDate = LocalDate.now();
    }

    public Order(String title, String description, LocalDate orderDate, LocalDate dueDate, OrderStatus status) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
