package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.enums.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderResponseFactoryTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    CompositionOfOrder compositionOfOrder;

    @InjectMocks
    private OrderResponseFactory orderResponseFactory;

    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderEntity = new OrderEntity();
        orderEntity.setOrderDate(LocalDate.now().minusDays(5));
        orderEntity.setDueDate(LocalDate.now().plusDays(10));
        orderEntity.setStatus(OrderStatus.ONGOING);
        orderEntity.setTotalProductPrice(100);
    }
    @Test
    void mapToOrderResponse_WhenNotOverdue_RecordTimeUtilizationRatio() {

        when(compositionOfOrder.creatSimplifiedCompositionOfOrder(orderEntity)).thenReturn("1. Эбонитовый кинжал из игры Скайрим/Skyrim - 1 шт.");

        OrderResponse orderResponse = orderResponseFactory.mapToOrderResponse(orderEntity);

        assertEquals("5/15", orderResponse.getTimeUtilizationRatio());
        assertEquals(OrderStatus.ONGOING, orderResponse.getStatus());
        assertEquals(100.0, orderResponse.getTotalProductPrice());
    }

    @Test
    void mapToOrderResponse_WhenOverdue_OverdueStatusAndSave() {
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setDueDate(LocalDate.now().minusDays(5));

        when(compositionOfOrder.creatSimplifiedCompositionOfOrder(orderEntity)).thenReturn("1. Эбонитовый кинжал из игры Скайрим/Skyrim - 1 шт.");

        OrderResponse orderResponse = orderResponseFactory.mapToOrderResponse(orderEntity);

        assertEquals("*/*", orderResponse.getTimeUtilizationRatio());
        assertEquals(OrderStatus.OVERDUE, orderResponse.getStatus());
        verify(orderRepository, times(1)).save(orderEntity);
    }

    @Test
    void mapToOrderResponse_WhenOverdue_OldStatusAndSave() {
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setDueDate(LocalDate.now().minusDays(5));
        orderEntity.setStatus(OrderStatus.CANCELLED);

        when(compositionOfOrder.creatSimplifiedCompositionOfOrder(orderEntity)).thenReturn("1. Эбонитовый кинжал из игры Скайрим/Skyrim - 1 шт.");

        OrderResponse orderResponse = orderResponseFactory.mapToOrderResponse(orderEntity);

        assertEquals(OrderStatus.CANCELLED, orderResponse.getStatus());
    }
}