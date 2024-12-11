package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.ClientEntity;

public class ValidationClientById {

    static public void validator(ClientEntity clientEntity, Long clientId) {
        if (clientEntity == null) {
            throw new IllegalArgumentException("Клиент с ID " + clientId + " не найден");
        }
    }
}
