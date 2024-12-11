package com.kazimirov.financemanagement.entity;

import com.kazimirov.financemanagement.repository.ClientRepository;
import com.kazimirov.financemanagement.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ClientEntityTest {

    private ClientRepository clientRepository;
    private ClientService clientService;

    @BeforeEach
    void setUp() {

        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void testCreateClientWithCorrectLink() {

        ClientEntity inputClient = new ClientEntity();
        inputClient.setName("Test Client");
        inputClient.setLinkToProfile("http://example.com");
        inputClient.setNote("Test Note");
    }

    @Test
    void testCreateClientWithInvalidUrl() {

        ClientEntity inputClient = new ClientEntity();
        inputClient.setName("Test Client");

        try {
            inputClient.setLinkToProfile("2");
            Assertions.fail("Тест провален: Ссылка корректная");
        } catch (IllegalArgumentException e) {
        }
    }
}
