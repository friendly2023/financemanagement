package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class OrderEntityRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private List<OrderEntity> createdOrderEntities = new ArrayList<>();

    @Test
    public void testSaveTask() {
        // Создаем новую задачу
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTitle("Тестовая задача");
        orderEntity.setNote("Описание тестовой задачи");
        orderEntity.setDueDate(LocalDate.now().plusDays(5));
        orderEntity.setStatus(OrderStatus.ONGOING);

        // Сохраняем задачу в базе
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        createdOrderEntities.add(savedOrderEntity);

        // Проверяем, что задача сохранена
        assertNotNull(savedOrderEntity);
        assertNotNull(savedOrderEntity.getId());
        assertEquals("Тестовая задача", savedOrderEntity.getTitle());
        assertEquals("Описание тестовой задачи", savedOrderEntity.getNote());
        assertEquals(LocalDate.now().plusDays(5), savedOrderEntity.getDueDate());
        assertEquals(OrderStatus.ONGOING, savedOrderEntity.getStatus());
    }

    @Test
    public void testRequestByTitleTask() {
        OrderEntity orderEntity1 = new OrderEntity();
        orderEntity1.setTitle("Тестовая задача 1");
        orderEntity1.setNote("Описание задачи 1");
        orderEntity1.setDueDate(LocalDate.now().plusDays(5));
        orderEntity1.setStatus(OrderStatus.ONGOING);
        orderRepository.save(orderEntity1);

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setTitle("Тестовая задача 2");
        orderEntity2.setNote("Описание задачи 2");
        orderEntity2.setDueDate(LocalDate.now().plusDays(3));
        orderEntity2.setStatus(OrderStatus.ONGOING);
        orderRepository.save(orderEntity2);

        OrderEntity orderEntity3 = new OrderEntity();
        orderEntity3.setTitle("Неподходящая задача");
        orderEntity3.setNote("Описание задачи 3");
        orderEntity3.setDueDate(LocalDate.now().plusDays(10));
        orderEntity3.setStatus(OrderStatus.ONGOING);
        orderRepository.save(orderEntity3);

        OrderEntity savedOrder1Entity = orderRepository.save(orderEntity1);
        OrderEntity savedOrder2Entity = orderRepository.save(orderEntity2);
        OrderEntity savedOrder3Entity = orderRepository.save(orderEntity3);

        createdOrderEntities.add(savedOrder1Entity);
        createdOrderEntities.add(savedOrder2Entity);
        createdOrderEntities.add(savedOrder3Entity);

        List<OrderEntity> foundOrderEntities = orderRepository.findByTitleContaining("Тестовая");

        assertNotNull(foundOrderEntities);
        assertEquals(2, foundOrderEntities.size());
        assertTrue(foundOrderEntities.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 1")));
        assertTrue(foundOrderEntities.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 2")));
    }

    @Test
    public void testRequestByStatusTask() {
        OrderEntity orderEntity1 = new OrderEntity();
        orderEntity1.setTitle("Тестовая задача 1");
        orderEntity1.setNote("Описание задачи 1");
        orderEntity1.setDueDate(LocalDate.now().plusDays(5));
        orderEntity1.setStatus(OrderStatus.ONGOING);
        orderRepository.save(orderEntity1);

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setTitle("Тестовая задача 2");
        orderEntity2.setNote("Описание задачи 2");
        orderEntity2.setDueDate(LocalDate.now().plusDays(3));
        orderEntity2.setStatus(OrderStatus.OVERDUE);
        orderRepository.save(orderEntity2);

        OrderEntity orderEntity3 = new OrderEntity();
        orderEntity3.setTitle("Неподходящая задача");
        orderEntity3.setNote("Описание задачи 3");
        orderEntity3.setDueDate(LocalDate.now().plusDays(10));
        orderEntity3.setStatus(OrderStatus.ONGOING);
        orderRepository.save(orderEntity3);

        OrderEntity savedOrder1Entity = orderRepository.save(orderEntity1);
        OrderEntity savedOrder2Entity = orderRepository.save(orderEntity2);
        OrderEntity savedOrder3Entity = orderRepository.save(orderEntity3);

        createdOrderEntities.add(savedOrder1Entity);
        createdOrderEntities.add(savedOrder2Entity);
        createdOrderEntities.add(savedOrder3Entity);

        List<OrderEntity> foundOrderEntities = orderRepository.findByStatus(OrderStatus.ONGOING);

        assertNotNull(foundOrderEntities);
        assertEquals(2, foundOrderEntities.size());
        assertTrue(foundOrderEntities.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 1")));
        assertTrue(foundOrderEntities.stream().anyMatch(task -> task.getTitle().equals("Неподходящая задача")));
    }

    @AfterEach
    public void cleanUp() {
        // Удаляем только те задачи, которые были созданы в процессе тестов
        for (OrderEntity orderEntity : createdOrderEntities) {
            orderRepository.delete(orderEntity);
        }
        createdOrderEntities.clear();
    }

}
