package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.OrderStatus;
import com.kazimirov.financemanagement.entity.ProductEntity;
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

    @Query("SELECT p FROM ProductEntity p WHERE p.orderEntity.id = :orderId")
    List<ProductEntity> findAllProductsByOrderId(@Param("orderId") Long orderId);

//todo удалить за ненадобностью
//    ClientEntity findClientById(Long clientId);

}
