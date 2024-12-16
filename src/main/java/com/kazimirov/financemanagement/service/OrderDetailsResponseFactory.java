package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderDetailsResponse;
import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailsResponseFactory {

    OrderRepository orderRepository;
    OrderResponseFactory orderResponseFactory;
    CompositionOfOrder compositionOfOrder;

    @Autowired
    public OrderDetailsResponseFactory(OrderRepository orderRepository,
                                       OrderResponseFactory orderResponseFactory,
                                       CompositionOfOrder compositionOfOrder) {
        this.orderRepository = orderRepository;
        this.orderResponseFactory = orderResponseFactory;
        this.compositionOfOrder = compositionOfOrder;
    }

    public OrderDetailsResponse mapToOrderDetailsResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = orderResponseFactory.mapToOrderResponse(orderEntity);

        return new OrderDetailsResponse(
                orderResponse.getId(),
                orderEntity.getNote(),
                orderResponse.getStatus(),
                orderResponse.getDueDate(),
                orderEntity.getOrderDate(),
                orderEntity.getCity(),
                orderResponse.getTimeUtilizationRatio(),
                orderEntity.getTotalProductPrice(),
                compositionOfOrder.creatCompositionOfOrder(orderEntity),
                orderEntity.getClient()
        );
    }
}
