package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ValidationClientById validationClientById;

    @Autowired
    public ClientService(ClientRepository clientRepository, ValidationClientById validationClientById) {
        this.clientRepository = clientRepository;
        this.validationClientById = validationClientById;
    }

    public ClientEntity createClient(ClientEntity clientEntity) {
        clientEntity.setLinkToProfile(ValidatorUrl.validatorURI(clientEntity.getLinkToProfile()));
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

    public ClientEntity getClientById(Long clientId) {
        validationClientById.checkClientExists(clientId);
        return clientRepository.findById(clientId)
                .get();
    }

    public void deleteClient(Long clientId) {
        validationClientById.checkClientExists(clientId);
        clientRepository.deleteById(clientId);
    }

}
