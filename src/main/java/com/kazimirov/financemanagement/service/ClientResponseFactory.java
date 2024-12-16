package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientResponseFactory {

    OrderService orderService;

    @Autowired
    public ClientResponseFactory(OrderService orderService) {
        this.orderService = orderService;
    }

    public ClientResponse mapClientDTO(ClientEntity clientEntity) {

        Long clientId = clientEntity.getId();
        List<OrderResponse> orderResponses = orderService.getForClientOrdersByIdSortedByDueDate(clientId);

        int numberOfOrders = orderResponses.size();
        int amountOrders = 0;

        for (OrderResponse order:orderResponses){
            amountOrders += order.getTotalProductPrice();
        }

        return new ClientResponse(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getLinkToProfile(),
                clientEntity.getNote(),
                numberOfOrders,
                amountOrders
        );
    }
}
