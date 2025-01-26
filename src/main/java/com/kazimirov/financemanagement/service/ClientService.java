package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ValidatorClientById validatorClientById;
    private  ClientResponseFactory clientResponseFactory;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ValidatorClientById validatorClientById,
                         ClientResponseFactory clientResponseFactory) {
        this.clientRepository = clientRepository;
        this.validatorClientById = validatorClientById;
        this.clientResponseFactory = clientResponseFactory;
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
                .map(clientResponseFactory::mapClientDTO)
                .collect(Collectors.toList());
    }

    public ClientEntity getClientById(Long clientId) {
        validatorClientById.checkClientExists(clientId);
        return clientRepository.findById(clientId)
                .get();
    }

    public void deleteClient(Long clientId) {
        validatorClientById.checkClientExists(clientId);
        clientRepository.deleteById(clientId);
    }

    public Optional<ClientEntity> findClientByLinkOrNameAndNote(String linkToProfile, String name, String note) {

        if (linkToProfile != null && !linkToProfile.trim().isEmpty()) {
            return clientRepository.findByLinkToProfileIgnoreCase(linkToProfile);
        }

        if (name != null && !name.isEmpty() && note != null && !note.isEmpty()) {
            return clientRepository.findByNameAndNoteIgnoreCase(name, note);
        }

        if (name != null && !name.isEmpty()) {
            return clientRepository.findByNameIgnoreCase(name);
        }

        return Optional.empty();
    }


    public List<ClientResponse> searchClients(String query) {
        List<ClientEntity> clientEntity = clientRepository.searchByNameOrLinkOrNote(query);

        return clientEntity.stream()
                .map(clientResponseFactory::mapClientDTO)
                .collect(Collectors.toList());
    }
}
