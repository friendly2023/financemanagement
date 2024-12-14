package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;

public class ClientResponseFactory {

    static public ClientResponse mapClientDTO(ClientEntity clientEntity) {
        //todo прописать подсчет колличества заказов клиента
        int numberOfOrders = 5;
        //todo прописать подсчет суммы заказов клиента
        int amountOrders = 77;

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
