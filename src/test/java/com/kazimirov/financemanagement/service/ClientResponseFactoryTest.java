package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientResponseFactoryTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    private  ClientResponseFactory clientResponseFactory;

    private ClientEntity clientEntity;
    private OrderResponse orderResponse;

    @Test
    void mapClientDTOTest() throws NoSuchFieldException, IllegalAccessException {
        orderResponse = new OrderResponse();

        Field field = OrderResponse.class.getDeclaredField("totalProductPrice");
        field.setAccessible(true);
        field.set(orderResponse, 5000);

        List<OrderResponse> orderResponses = Collections.singletonList(orderResponse);
        clientEntity = new ClientEntity();

        when(orderService.getForClientOrdersByIdSortedByDueDate(clientEntity.getId())).thenReturn(orderResponses);

        ClientResponse clientResponse = clientResponseFactory.mapClientDTO(clientEntity);

        assertEquals(5000, clientResponse.getAmountOrders());
    }
}