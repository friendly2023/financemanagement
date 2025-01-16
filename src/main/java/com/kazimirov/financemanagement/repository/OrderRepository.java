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

    List<OrderEntity> findAllByOrderByDueDate();

    List<OrderEntity> findByClientEntity_Id(Long clientId);

    @Query("SELECT p FROM ProductEntity p WHERE p.orderEntity.id = :orderId")
    List<ProductEntity> findAllProductsByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT COUNT(o) FROM OrderEntity o")
    Integer countAllOrders();


    @Query("SELECT COUNT(o) FROM OrderEntity o where o.status='COMPLETED'")
    Integer countAllCompletedOrders();
    @Query("SELECT COUNT(o) FROM OrderEntity o where o.status='ONGOING'")
    Integer countAllOngoingOrders();
    @Query("SELECT COUNT(o) FROM OrderEntity o where o.status='CANCELLED'")
    Integer countAllCancelledOrders();
    @Query("SELECT COUNT(o) FROM OrderEntity o where o.status='OVERDUE'")
    Integer countAllOverdueOrders();

    @Query("select SUM(o.totalProductPrice) from OrderEntity o where o.status='COMPLETED'")
    Integer getTotalEarnings();

    @Query("SELECT SUM(p.quantity) FROM OrderEntity o " +
            "JOIN o.productEntities p " +
            "WHERE o.status = 'COMPLETED'")
    Integer getTotalProductSold();

}
