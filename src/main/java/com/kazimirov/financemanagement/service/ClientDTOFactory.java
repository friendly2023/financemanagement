package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientDTO;
import com.kazimirov.financemanagement.model.Client;

public class ClientDTOFactory {

    static public ClientDTO mapClientDTO(Client client) {
        //todo прописать подсчет колличества заказов клиента
        int numberOfOrders = 5;
        //todo прописать подсчет суммы заказов клиента
        int amountOrders = 77;

        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getLinkToProfile(),
                client.getNote(),
                numberOfOrders,
                amountOrders
        );
    }
}
