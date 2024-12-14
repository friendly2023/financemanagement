package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findAllByOrderByDueDate();

    List<OrderEntity> findByClientEntity_Id(Long clientId);

//todo удалить за ненадобностью
//    ClientEntity findClientById(Long clientId);

}
