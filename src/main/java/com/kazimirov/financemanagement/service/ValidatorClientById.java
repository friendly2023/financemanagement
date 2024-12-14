package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidatorClientById {

    ClientRepository clientRepository;

    public ValidatorClientById(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void checkClientExists(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            //TODO обработать вывод ошибки на веб-страницу
            throw new IllegalArgumentException("Клиент с ID = " + clientId + " не найден.");
        }
    }
}
