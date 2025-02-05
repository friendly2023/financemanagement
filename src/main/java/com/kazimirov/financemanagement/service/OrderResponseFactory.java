package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.enums.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.kazimirov.financemanagement.enums.OrderStatus.ONGOING;

@Component
public class OrderResponseFactory {

    OrderRepository orderRepository;
    CompositionOfOrder compositionOfOrder;

    @Autowired
    public OrderResponseFactory(OrderRepository orderRepository, CompositionOfOrder compositionOfOrder) {
        this.orderRepository = orderRepository;
        this.compositionOfOrder = compositionOfOrder;
    }

    public OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
        long daysLeftFromNow = ChronoUnit.DAYS.between(orderEntity.getOrderDate(), LocalDate.now())+1;
        long daysLeftFromOrderDate = ChronoUnit.DAYS.between(orderEntity.getOrderDate(), orderEntity.getDueDate());

        String timeUtilizationRatio;

        if (orderEntity.getDueDate().isAfter(LocalDate.now()) && orderEntity.getStatus()==ONGOING) {
            timeUtilizationRatio = daysLeftFromNow + "/" + daysLeftFromOrderDate;
        } else {
            timeUtilizationRatio = "*/*";
            if (orderEntity.getStatus() == ONGOING) {
                orderEntity.setStatus(OrderStatus.OVERDUE);
                orderRepository.save(orderEntity);
            }
        }

        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getStatus(),
                orderEntity.getOrderDate(),
                timeUtilizationRatio,
                orderEntity.getTotalProductPrice(),
                compositionOfOrder.creatSimplifiedCompositionOfOrder(orderEntity)
        );
    }
}
