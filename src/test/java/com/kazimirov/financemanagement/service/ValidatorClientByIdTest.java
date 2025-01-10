package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorClientByIdTest {
    private Long clientId = 1L;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ValidatorClientById validatorClientById;

    @Test
    void checkClientExists_ClientExists_ShouldPass() {

        when(clientRepository.existsById(clientId)).thenReturn(true);

        validatorClientById.checkClientExists(clientId);
    }

    @Test
    void checkClientExists_ClientExists_Error() {

        when(clientRepository.existsById(clientId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validatorClientById.checkClientExists(clientId));

        assertEquals("Клиент с ID = " + clientId + " не найден.", exception.getMessage());
    }

}