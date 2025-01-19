package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderSearchService {

    private OrderService orderService;

    public OrderSearchService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderResponse> searchByOrders(String request) {
        List<OrderResponse> orders = orderService.getAllOrdersSortedByDueDate();
        String lowerCaseQuery = request.toLowerCase();

        return orders.stream()
                .filter(order -> order.getCompositionOfOrder().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
}
