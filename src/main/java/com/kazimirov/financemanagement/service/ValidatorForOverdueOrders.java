package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidatorForOverdueOrders {

    public void validate(OrderEntity orderEntity) {
        if (isOrderOverdue(orderEntity)) {
            throw new IllegalArgumentException("Дата оформления заказа должна быть раньше даты срока выполнения");
        }
    }

    private boolean isOrderOverdue(OrderEntity orderEntity) {
        return orderEntity.getOrderDate().isAfter(orderEntity.getDueDate());
    }
}
