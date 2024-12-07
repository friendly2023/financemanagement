package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.ClientEntity;
import com.kazimirov.financemanagement.model.OrderEntity;
import com.kazimirov.financemanagement.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByTitleContaining(String title);

    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findAllByOrderByDueDate();
//todo удалить за ненадобностью
//    ClientEntity findClientById(Long clientId);

}
