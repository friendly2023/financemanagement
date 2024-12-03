package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Client;

public class ValidationClientById {

    static public void validator(Client client, Long clientId) {
        if (client == null) {
            throw new IllegalArgumentException("Клиент с ID " + clientId + " не найден");
        }
    }
}
