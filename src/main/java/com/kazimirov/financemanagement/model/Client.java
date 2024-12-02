package com.kazimirov.financemanagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "link_to_profile", nullable = false)
    private String linkToProfile;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders  = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String note, List<Order> orders) {
        this.name = name;
        this.note = note;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getLinkToProfile() {
        return linkToProfile;
    }

    public void setLinkToProfile(String linkToProfile) {
        this.linkToProfile = linkToProfile;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);  // Устанавливаем обратную связь с клиентом
    }

    // Метод для удаления заказа
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setClient(null);  // Убираем обратную связь с клиентом
    }
}
