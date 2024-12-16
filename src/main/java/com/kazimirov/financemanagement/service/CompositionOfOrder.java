package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompositionOfOrder {

    OrderRepository orderRepository;

    @Autowired
    public CompositionOfOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String creatCompositionOfOrder(OrderEntity orderEntity){
        List<ProductEntity> productEntities = orderRepository.findAllProductsByOrderId(orderEntity.getId());

        StringBuilder сompositionOfOrder = new StringBuilder();

        for (int i = 0; i < productEntities.size(); i++) {
            сompositionOfOrder
                    .append(i+1 + ". ")
                    .append(productEntities.get(i).getProductName())
                    .append(" - ")
                    .append(productEntities.get(i).getPrice())
                    .append(" р/шт. - ")
                    .append(productEntities.get(i).getQuantity())
                    .append(" шт. - Итого: ")
                    .append(productEntities.get(i).getPrice()*productEntities.get(i).getQuantity())
                    .append(" р")
                    .append("\n");
        }

        return сompositionOfOrder.toString();
    }
}
