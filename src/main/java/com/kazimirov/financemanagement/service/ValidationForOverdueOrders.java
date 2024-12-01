package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidationForOverdueOrders {

    public void validate(Order order) {
        if (isOrderOverdue(order)) {
            handleOverdueTask(order);
        }
    }

    private boolean isOrderOverdue(Order order) {
        return order.getOrderDate().isAfter(order.getDueDate());
    }

    private void handleOverdueTask(Order order) {
        // TODO обработать вывод ошибки на веб-страницу
        if (order.getStatus() == OrderStatus.ONGOING) {
            throw new IllegalArgumentException("Невозможно данной задаче присвоить статус 'ONGOING'");
        }
    }
}
