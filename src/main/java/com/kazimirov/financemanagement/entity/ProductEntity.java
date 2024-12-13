package com.kazimirov.financemanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private OrderEntity orderEntity;

    public ProductEntity() {
    }

    public ProductEntity(String productName, int quantity, double price, String note, OrderEntity orderEntity) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
        this.orderEntity = orderEntity;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
