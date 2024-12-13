package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;

import java.time.LocalDate;
import java.time.Period;

public class OrderResponseFactory {

    static public OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
        int daysLeftFromNow = Period.between(orderEntity.getOrderDate(), LocalDate.now()).getDays();
        int daysLeftFromOrderDate = Period.between(orderEntity.getOrderDate(), orderEntity.getDueDate()).getDays();
        //todo изменить подсчет timeUtilizationRatio для корректного тображения, избегать 10/2
        String timeUtilizationRatio = daysLeftFromNow + "/" + daysLeftFromOrderDate;

        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getTitle(),
                orderEntity.getTotalProductPrice(),
                orderEntity.getStatus(),
                orderEntity.getDueDate(),
                timeUtilizationRatio
        );
    }
}
