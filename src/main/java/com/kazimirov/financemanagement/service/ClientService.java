package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.model.ClientEntity;
import com.kazimirov.financemanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity createClient(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public List<ClientResponse> getAllClientsById() {
        List<ClientEntity> clientEntity = clientRepository.findAllByOrderByIdDesc();

        return clientEntity.stream()
                .map(ClientDTOFactory::mapClientDTO)
                .collect(Collectors.toList());
    }

//TODO обработать вывод ошибки на веб-страницу
    public ClientEntity getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Клиент с ID = " + clientId + " не найден"));
    }
}
