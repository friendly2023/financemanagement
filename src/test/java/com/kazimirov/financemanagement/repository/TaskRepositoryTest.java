package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.Task;
import com.kazimirov.financemanagement.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

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

        // Проверяем, что задача сохранена
        assertNotNull(savedTask);
        assertNotNull(savedTask.getId());
        assertEquals("Тестовая задача", savedTask.getTitle());
        assertEquals("Описание тестовой задачи", savedTask.getDescription());
        assertEquals(LocalDate.now().plusDays(5), savedTask.getDueDate());
        assertEquals(TaskStatus.ONGOING, savedTask.getStatus());
    }
}
