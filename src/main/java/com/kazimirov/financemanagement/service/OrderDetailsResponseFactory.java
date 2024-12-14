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

    @Autowired
    public OrderDetailsResponseFactory(OrderRepository orderRepository,
                                       OrderResponseFactory orderResponseFactory) {
        this.orderRepository = orderRepository;
        this.orderResponseFactory = orderResponseFactory;
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
                creatCompositionOfOrder(orderEntity),
                orderEntity.getClient()
        );
    }

    private String creatCompositionOfOrder(OrderEntity orderEntity){
        List<ProductEntity> productEntities = orderRepository.findAllProductsByOrderId(orderEntity.getId());

        StringBuilder сompositionOfOrder = new StringBuilder();

        for (int i = 0; i < productEntities.size(); i++) {
            сompositionOfOrder.append(productEntities.get(i).getProductName())
                    .append(" - ")
                    .append(productEntities.get(i).getPrice())
                    .append("\n");
        }

        return сompositionOfOrder.toString();
    }
}
