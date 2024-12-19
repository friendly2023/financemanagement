package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidatorUrl.class)
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ValidatorClientById validatorClientById;
    @Mock
    private  ClientResponseFactory clientResponseFactory;
    @Mock
    private ValidatorUrl validatorUrl;
    @InjectMocks
    private ClientService clientService;

    private ClientEntity clientEntity;

    @Test
    void createClientTest() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLinkToProfile("http://example.com");

        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);

        ClientEntity result = clientService.createClient(clientEntity);

        verify(clientRepository).save(clientEntity);

        assertEquals(clientEntity, result);
    }

    @Test
    void getAllClientsTest() {

        clientEntity = new ClientEntity();
        clientEntity.setNote("Note");

        List<ClientEntity> clientEntities = List.of(clientEntity);

        when(clientRepository.findAll()).thenReturn(clientEntities);

        List<ClientEntity> clientEntitiesFromDB = clientService.getAllClients();

        assertEquals("Note", clientEntitiesFromDB.get(0).getNote());

        verify(clientRepository).findAll();
    }

    @Test
    void getAllClientsByIdTest() {
        clientEntity = new ClientEntity();
        ClientResponse clientResponse = new ClientResponse();

        List<ClientEntity> clientEntities = List.of(clientEntity);
        when(clientRepository.findAllByOrderByIdDesc()).thenReturn(clientEntities);
        when(clientResponseFactory.mapClientDTO(clientEntity)).thenReturn(clientResponse);

        List<ClientResponse> responses = clientService.getAllClientsById();

        assertEquals(1, responses.size());
        assertEquals(clientResponse, responses.get(0));
        verify(clientRepository).findAllByOrderByIdDesc();
        verify(clientResponseFactory).mapClientDTO(clientEntity);
    }

    @Test
    void getClientByIdTest() {
        Long clientId = 1L;

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName("Test Client");

        doNothing().when(validatorClientById).checkClientExists(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));

        ClientEntity result = clientService.getClientById(clientId);

        verify(validatorClientById).checkClientExists(clientId);
        verify(clientRepository).findById(clientId);

        assertNotNull(result);
        assertEquals(clientEntity, result);
    }

    @Test
    void deleteClient() {
        Long clientId = 1L;

        doNothing().when(validatorClientById).checkClientExists(clientId);

        clientService.deleteClient(clientId);

        verify(validatorClientById).checkClientExists(clientId);
        verify(clientRepository).deleteById(clientId);

    }
}