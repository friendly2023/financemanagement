package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Order;
import org.springframework.stereotype.Component;

@Component
public class ValidationForVerifyDescription {

    public void validate(Order order) {
        if (order.getDescription() == null || order.getDescription().trim().isEmpty()) {
            order.setDescription("*описание отсутствует*");
        }
    }
}
