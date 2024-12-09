package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.OrderEntity;
import com.kazimirov.financemanagement.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidationForOverdueOrders {

    public void validate(OrderEntity orderEntity) {
        if (isOrderOverdue(orderEntity)) {
            handleOverdueTask(orderEntity);
        }
    }

    private boolean isOrderOverdue(OrderEntity orderEntity) {
        return orderEntity.getOrderDate().isAfter(orderEntity.getDueDate());
    }

    private void handleOverdueTask(OrderEntity orderEntity) {
        // TODO обработать вывод ошибки на веб-страницу
        if (orderEntity.getStatus() == OrderStatus.ONGOING) {
            throw new IllegalArgumentException("Невозможно данной задаче присвоить статус 'ONGOING'");
        }
    }
}
