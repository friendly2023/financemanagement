package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderDTO;
import com.kazimirov.financemanagement.model.Order;

import java.time.LocalDate;
import java.time.Period;

public class OrderDTOFactory {

    static public OrderDTO mapToOrderDTO(Order order) {
        int daysLeftFromNow = Period.between(order.getOrderDate(), LocalDate.now()).getDays();
        int daysLeftFromOrderDate = Period.between(order.getOrderDate(), order.getDueDate()).getDays();

        String timeUtilizationRatio = daysLeftFromNow + "/" + daysLeftFromOrderDate;

        return new OrderDTO(
                order.getId(),
                order.getTitle(),
                order.getStatus(),
                order.getDueDate(),
                timeUtilizationRatio
        );
    }
}
