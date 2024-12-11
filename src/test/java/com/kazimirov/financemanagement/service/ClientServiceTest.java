package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceTest {

    private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
    private final ClientService clientService = new ClientService(clientRepository);

    @Test
    void testGetAllClientsById() {

        List<ClientEntity> mockClients = getClient();
        Mockito.when(clientRepository.findAllByOrderByIdDesc()).thenReturn(mockClients);

        List<ClientResponse> result = clientService.getAllClientsById();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockClients.size(), result.size());
        Assertions.assertEquals(mockClients.get(0).getName(), result.get(0).getName());
    }

    private List<ClientEntity> getClient() {

        ClientEntity client1 = new ClientEntity();
        client1.setName("Test Client1");
        client1.setLinkToProfile("http://example.com");
        client1.setNote("Test Note");

        return List.of(client1);
    }
}
