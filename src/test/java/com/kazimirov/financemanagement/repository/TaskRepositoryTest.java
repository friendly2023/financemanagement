package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.Task;
import com.kazimirov.financemanagement.model.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private List<Task> createdTasks = new ArrayList<>();

    @Test
    public void testSaveTask() {
        // Создаем новую задачу
        Task task = new Task();
        task.setTitle("Тестовая задача");
        task.setDescription("Описание тестовой задачи");
        task.setDueDate(LocalDate.now().plusDays(5));
        task.setStatus(TaskStatus.ONGOING);

        // Сохраняем задачу в базе
        Task savedTask = taskRepository.save(task);

        createdTasks.add(savedTask);

        // Проверяем, что задача сохранена
        assertNotNull(savedTask);
        assertNotNull(savedTask.getId());
        assertEquals("Тестовая задача", savedTask.getTitle());
        assertEquals("Описание тестовой задачи", savedTask.getDescription());
        assertEquals(LocalDate.now().plusDays(5), savedTask.getDueDate());
        assertEquals(TaskStatus.ONGOING, savedTask.getStatus());
    }

    @Test
    public void testRequestByTitleTask() {
        Task task1 = new Task();
        task1.setTitle("Тестовая задача 1");
        task1.setDescription("Описание задачи 1");
        task1.setDueDate(LocalDate.now().plusDays(5));
        task1.setStatus(TaskStatus.ONGOING);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Тестовая задача 2");
        task2.setDescription("Описание задачи 2");
        task2.setDueDate(LocalDate.now().plusDays(3));
        task2.setStatus(TaskStatus.ONGOING);
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTitle("Неподходящая задача");
        task3.setDescription("Описание задачи 3");
        task3.setDueDate(LocalDate.now().plusDays(10));
        task3.setStatus(TaskStatus.ONGOING);
        taskRepository.save(task3);

        Task savedTask1 = taskRepository.save(task1);
        Task savedTask2 = taskRepository.save(task2);
        Task savedTask3 = taskRepository.save(task3);

        createdTasks.add(savedTask1);
        createdTasks.add(savedTask2);
        createdTasks.add(savedTask3);

        List<Task> foundTasks = taskRepository.findByTitleContaining("Тестовая");

        assertNotNull(foundTasks);
        assertEquals(2, foundTasks.size());
        assertTrue(foundTasks.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 1")));
        assertTrue(foundTasks.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 2")));
    }

    @Test
    public void testRequestByStatusTask() {
        Task task1 = new Task();
        task1.setTitle("Тестовая задача 1");
        task1.setDescription("Описание задачи 1");
        task1.setDueDate(LocalDate.now().plusDays(5));
        task1.setStatus(TaskStatus.ONGOING);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Тестовая задача 2");
        task2.setDescription("Описание задачи 2");
        task2.setDueDate(LocalDate.now().plusDays(3));
        task2.setStatus(TaskStatus.OVERDUE);
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTitle("Неподходящая задача");
        task3.setDescription("Описание задачи 3");
        task3.setDueDate(LocalDate.now().plusDays(10));
        task3.setStatus(TaskStatus.ONGOING);
        taskRepository.save(task3);

        Task savedTask1 = taskRepository.save(task1);
        Task savedTask2 = taskRepository.save(task2);
        Task savedTask3 = taskRepository.save(task3);

        createdTasks.add(savedTask1);
        createdTasks.add(savedTask2);
        createdTasks.add(savedTask3);

        List<Task> foundTasks = taskRepository.findByStatus(TaskStatus.ONGOING);

        assertNotNull(foundTasks);
        assertEquals(2, foundTasks.size());
        assertTrue(foundTasks.stream().anyMatch(task -> task.getTitle().equals("Тестовая задача 1")));
        assertTrue(foundTasks.stream().anyMatch(task -> task.getTitle().equals("Неподходящая задача")));
    }

    @AfterEach
    public void cleanUp() {
        // Удаляем только те задачи, которые были созданы в процессе тестов
        for (Task task : createdTasks) {
            taskRepository.delete(task);
        }
        createdTasks.clear();
    }

}
