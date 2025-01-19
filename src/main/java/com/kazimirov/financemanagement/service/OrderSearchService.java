package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.enums.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderSearchService {

    private OrderService orderService;

    public OrderSearchService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderResponse> searchByOrders(String status,
                                              String query,
                                              LocalDate startDate,
                                              LocalDate endDate) {

        List<OrderResponse> allOrders = orderService.getAllOrdersSortedByDueDate();

        if (status != null && !status.isEmpty()) {
            OrderStatus filterStatus = OrderStatus.valueOf(status);
            allOrders = allOrders.stream()
                    .filter(order -> order.getStatus().equals(filterStatus))
                    .collect(Collectors.toList());
        }

        if (query != null && !query.isEmpty()) {
            String lowerCaseQuery = query.toLowerCase();
            allOrders = allOrders.stream()
                    .filter(order -> order.getCompositionOfOrder().toLowerCase().contains(lowerCaseQuery))
                    .collect(Collectors.toList());
        }

        if (startDate != null) {
            allOrders = allOrders.stream()
                    .filter(order -> !order.getOrderDate().isBefore(startDate))
                    .collect(Collectors.toList());
        }

        if (endDate != null) {
            allOrders = allOrders.stream()
                    .filter(order -> !order.getOrderDate().isAfter(endDate))
                    .collect(Collectors.toList());
        }

        return allOrders;
    }
}
