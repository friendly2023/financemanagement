package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ValidatorForOverdueOrders validatorForOverdueOrders;
    @Mock
    private ValidatorForVerifyNote validatorForVerifyNote;
    @Mock
    private OrderResponseFactory orderResponseFactory;
    @Mock
    private OrderDetailsResponseFactory orderDetailsResponseFactory;
    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntity;
    private OrderEntity orderEntity1;

    @Test
    void createOrder_save() {
        orderEntity = new OrderEntity();

        doNothing().when(validatorForOverdueOrders).validate(orderEntity);
        doNothing().when(validatorForVerifyNote).validate(orderEntity);

        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

        OrderEntity result = orderService.createOrder(orderEntity);


        verify(validatorForOverdueOrders).validate(orderEntity);
        verify(validatorForVerifyNote).validate(orderEntity);

        verify(orderRepository).save(orderEntity);

        assertEquals(orderEntity, result);
    }

    @Test
    void createOrder_shouldThrowException() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setDueDate(LocalDate.now().minusDays(5));

        doThrow(new IllegalArgumentException("Дата оформления заказа должна быть раньше даты срока выполнения"))
                .when(validatorForOverdueOrders).validate(orderEntity);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(orderEntity);
        });

        verify(orderRepository, never()).save(orderEntity);
    }

    @Test
    void getAllOrders_true() {
        orderEntity = new OrderEntity();
        orderEntity.setNote("Note");

        List<OrderEntity> orderEntities = List.of(orderEntity);

        when(orderRepository.findAll()).thenReturn(orderEntities);

        List<OrderEntity> orderEntitiesFromDB = orderService.getAllOrders();

        assertEquals("Note", orderEntitiesFromDB.get(0).getNote());

        verify(orderRepository).findAll();
    }

    @Test
    void searchOrderById_ReturnsOrder() throws NoSuchFieldException, IllegalAccessException{
        orderEntity = new OrderEntity();

        Field field = OrderEntity.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(orderEntity, 1L);
        orderEntity.setNote("Test Note");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

        OrderEntity result = orderService.searchOrderById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Note", result.getNote());

        verify(orderRepository).findById(1L);
    }

    @Test
    void searchOrderById_NotReturnsOrderAndException() throws NoSuchFieldException, IllegalAccessException{
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Проверка, что выбрасывается исключение
        assertThrows(NoSuchElementException.class, () -> orderService.searchOrderById(1L));

        // Проверка взаимодействия с mock
        verify(orderRepository).findById(1L);
    }

    @Test
    void getAllOrdersSortedByDueDateTest() {
        OrderEntity orderEntity = new OrderEntity();
        OrderResponse orderResponse = new OrderResponse();

        List<OrderEntity> orderEntities = List.of(orderEntity);
        when(orderRepository.findAllByOrderByDueDate()).thenReturn(orderEntities);
        when(orderResponseFactory.mapToOrderResponse(orderEntity)).thenReturn(orderResponse);

        List<OrderResponse> responses = orderService.getAllOrdersSortedByDueDate();

        assertEquals(1, responses.size());
        assertEquals(orderResponse, responses.get(0));
        verify(orderRepository).findAllByOrderByDueDate();
        verify(orderResponseFactory).mapToOrderResponse(orderEntity);
    }

    @Test
    void getForClientOrdersByIdSortedByDueDateTest() {
        Long clientId = 1L;
        OrderEntity orderEntity = new OrderEntity();
        OrderResponse orderResponse = new OrderResponse();
        List<OrderEntity> orderEntities = List.of(orderEntity);

        when(orderRepository.findByClientEntity_Id(clientId)).thenReturn(orderEntities);
        when(orderResponseFactory.mapToOrderResponse(orderEntity)).thenReturn(orderResponse);

        List<OrderResponse> responses = orderService.getForClientOrdersByIdSortedByDueDate(clientId);

        assertEquals(1, responses.size());
        assertEquals(orderResponse, responses.get(0));
        verify(orderRepository).findByClientEntity_Id(clientId);
        verify(orderResponseFactory).mapToOrderResponse(orderEntity);
    }

    @Test
    void getOrderById() {

    }










    @Test
    void getOrderDetailsById() {
    }

    @Test
    void findAllProductsByOrderId() {
    }

    @Test
    void deleteOrder() {
    }
}