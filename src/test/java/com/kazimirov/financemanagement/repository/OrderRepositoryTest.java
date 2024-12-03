package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.model.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private List<Order> createdOrders = new ArrayList<>();

    @Test
    public void testSaveTask() {
        // Создаем новую задачу
        Order order = new Order();
        order.setTitle("Тестовая задача");
        order.setNote("Описание тестовой задачи");
        order.setDueDate(LocalDate.now().plusDays(5));
        order.setStatus(OrderStatus.ONGOING);

        // Сохраняем задачу в базе
        Order savedOrder = orderRepository.save(order);

        createdOrders.add(savedOrder);

        // Проверяем, что задача сохранена
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertEquals("Тестовая задача", savedOrder.getTitle());
        assertEquals("Описание тестовой задачи", savedOrder.getNote());
        assertEquals(LocalDate.now().plusDays(5), savedOrder.getDueDate());
        assertEquals(OrderStatus.ONGOING, savedOrder.getStatus());
    }

    @Test
    public void testRequestByTitleTask() {
        Order order1 = new Order();
        order1.setTitle("Тестовая задача 1");
        order1.setNote("Описание задачи 1");
        order1.setDueDate(LocalDate.now().plusDays(5));
        order1.setStatus(OrderStatus.ONGOING);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setTitle("Тестовая задача 2");
        order2.setNote("Описание задачи 2");
        order2.setDueDate(LocalDate.now().plusDays(3));
        order2.setStatus(OrderStatus.ONGOING);
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setTitle("Неподходящая задача");
        order3.setNote("Описание задачи 3");
        order3.setDueDate(LocalDate.now().plusDays(10));
        order3.setStatus(OrderStatus.ONGOING);
        orderRepository.save(order3);

        Order savedOrder1 = orderRepository.save(order1);
        Order savedOrder2 = orderRepository.save(order2);
        Order savedOrder3 = orderRepository.save(order3);

        createdOrders.add(savedOrder1);
        createdOrders.add(savedOrder2);
        createdOrders.add(savedOrder3);

        List<Order> foundOrders = orderRepository.findByTitleContaining("Тестовая");

        assertNotNull(foundOrders);
        assertEquals(2, foundOrders.size());
        assertTrue(foundOrders.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 1")));
        assertTrue(foundOrders.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 2")));
    }

    @Test
    public void testRequestByStatusTask() {
        Order order1 = new Order();
        order1.setTitle("Тестовая задача 1");
        order1.setNote("Описание задачи 1");
        order1.setDueDate(LocalDate.now().plusDays(5));
        order1.setStatus(OrderStatus.ONGOING);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setTitle("Тестовая задача 2");
        order2.setNote("Описание задачи 2");
        order2.setDueDate(LocalDate.now().plusDays(3));
        order2.setStatus(OrderStatus.OVERDUE);
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setTitle("Неподходящая задача");
        order3.setNote("Описание задачи 3");
        order3.setDueDate(LocalDate.now().plusDays(10));
        order3.setStatus(OrderStatus.ONGOING);
        orderRepository.save(order3);

        Order savedOrder1 = orderRepository.save(order1);
        Order savedOrder2 = orderRepository.save(order2);
        Order savedOrder3 = orderRepository.save(order3);

        createdOrders.add(savedOrder1);
        createdOrders.add(savedOrder2);
        createdOrders.add(savedOrder3);

        List<Order> foundOrders = orderRepository.findByStatus(OrderStatus.ONGOING);

        assertNotNull(foundOrders);
        assertEquals(2, foundOrders.size());
        assertTrue(foundOrders.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 1")));
        assertTrue(foundOrders.stream().anyMatch(task -> task.getTitle().equals("Неподходящая задача")));
    }

    @AfterEach
    public void cleanUp() {
        // Удаляем только те задачи, которые были созданы в процессе тестов
        for (Order order : createdOrders) {
            orderRepository.delete(order);
        }
        createdOrders.clear();
    }

}
