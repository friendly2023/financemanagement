package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidationForOverdueOrdersTest {

    @Test
    void testValidate_withOverdueOrder_shouldThrowException() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        LocalDate orderDate = LocalDate.of(2023, 12, 1); // Поставим дату позже изначальной
        LocalDate dueDate = LocalDate.of(2023, 11, 30); // Дата просрочена

        when(orderEntity.getOrderDate()).thenReturn(orderDate);
        when(orderEntity.getDueDate()).thenReturn(dueDate);

        ValidationForOverdueOrders validator = new ValidationForOverdueOrders();


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(orderEntity);
        });

        assertEquals("Дата оформления заказа должна быть раньше даты срока выполнения", exception.getMessage());
    }

    @Test
    void testValidate_withNonOverdueOrder_shouldNotThrowException() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        LocalDate orderDate = LocalDate.of(2023, 11, 28); // Дата оформления заказа раньше
        LocalDate dueDate = LocalDate.of(2023, 12, 1); // Дата срока выполнения позже

        when(orderEntity.getOrderDate()).thenReturn(orderDate);
        when(orderEntity.getDueDate()).thenReturn(dueDate);

        ValidationForOverdueOrders validator = new ValidationForOverdueOrders();

        assertDoesNotThrow(() -> validator.validate(orderEntity));
    }

    @Test
    void testValidate_withEqualOrderDateAndDueDate_shouldNotThrowException() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        LocalDate orderDate = LocalDate.of(2023, 12, 1); // Дата оформления заказа
        LocalDate dueDate = LocalDate.of(2023, 12, 1); // Дата срока выполнения совпадает

        when(orderEntity.getOrderDate()).thenReturn(orderDate);
        when(orderEntity.getDueDate()).thenReturn(dueDate);

        ValidationForOverdueOrders validator = new ValidationForOverdueOrders();

        assertDoesNotThrow(() -> validator.validate(orderEntity));
    }
}
