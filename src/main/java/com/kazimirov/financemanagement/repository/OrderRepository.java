package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.Client;
import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTitleContaining(String title);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findAllByOrderByDueDate();
//todo удалить за ненадобностью
    Client findClientById(Long clientId);

}
