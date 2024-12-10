package com.kazimirov.financemanagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "link_to_profile")
    private String linkToProfile;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntities = new ArrayList<>();

    public ClientEntity() {
    }

    public ClientEntity(String name, String note, List<OrderEntity> orderEntities) {
        this.name = name;
        this.note = note;
        this.orderEntities = orderEntities;
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

    public List<OrderEntity> getOrders() {
        return orderEntities;
    }

    public void setOrders(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public String getLinkToProfile() {
        return linkToProfile;
    }

    public void setLinkToProfile(String linkToProfile) {
        this.linkToProfile = linkToProfile;
    }

    public void addOrder(OrderEntity orderEntity) {
        orderEntities.add(orderEntity);
        orderEntity.setClient(this);  // Устанавливаем обратную связь с клиентом
    }

    // Метод для удаления заказа
    public void removeOrder(OrderEntity orderEntity) {
        orderEntities.remove(orderEntity);
        orderEntity.setClient(null);  // Убираем обратную связь с клиентом
    }
}
