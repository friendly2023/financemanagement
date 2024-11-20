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

    @AfterEach
    public void cleanUp() {
        // Удаляем только те задачи, которые были созданы в процессе тестов
        for (Task task : createdTasks) {
            taskRepository.delete(task);
        }
        createdTasks.clear();
    }

}
