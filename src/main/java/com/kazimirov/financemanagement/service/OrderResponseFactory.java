package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class OrderResponseFactory {

    OrderRepository orderRepository;

    @Autowired
    public OrderResponseFactory(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
        int daysLeftFromNow = Period.between(orderEntity.getOrderDate(), LocalDate.now()).getDays();
        int daysLeftFromOrderDate = Period.between(orderEntity.getOrderDate(), orderEntity.getDueDate()).getDays();

        String timeUtilizationRatio;

        if (daysLeftFromNow <= daysLeftFromOrderDate) {
            timeUtilizationRatio = daysLeftFromNow + "/" + daysLeftFromOrderDate;
        } else {
            timeUtilizationRatio = "*/*";
            if (orderEntity.getStatus() == OrderStatus.ONGOING) {
                orderEntity.setStatus(OrderStatus.OVERDUE);
                orderRepository.save(orderEntity);
            }
        }

        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getStatus(),
                orderEntity.getDueDate(),
                timeUtilizationRatio,
                orderEntity.getTotalProductPrice()
        );
    }
}
