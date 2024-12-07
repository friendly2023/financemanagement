package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientDTO;
import com.kazimirov.financemanagement.model.Client;
import com.kazimirov.financemanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<ClientDTO> getAllClientsById() {
        List<Client> client = clientRepository.findAllByOrderByIdDesc();

        return client.stream()
                .map(ClientDTOFactory::mapClientDTO)
                .collect(Collectors.toList());
    }

//TODO обработать вывод ошибки на веб-страницу
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Клиент с ID = " + clientId + " не найден"));
    }
}
