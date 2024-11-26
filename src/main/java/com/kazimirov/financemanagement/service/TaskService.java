package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Task;
import com.kazimirov.financemanagement.model.TaskStatus;
import com.kazimirov.financemanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private Task task;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        checkingForOverdue(task);
        return taskRepository.save(task);
    }

    private void checkingForOverdue(Task task) {

        if (task.getOrderDate().isAfter((task.getDueDate()))) {
            task.setStatus(TaskStatus.OVERDUE);
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Возвращает все записи из базы
    }

    public List<Task> getAllTasksSortedByDueDateDesc() {
        return taskRepository.findAllByOrderByDueDateDesc();
    }

    public List<Task> getTaskByTitle(String title) {
        return taskRepository.findByTitleContaining(title);
    }

    public List<Task> getTaskByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Задача с ID " + id + " не найдена.");
        }
        taskRepository.deleteById(id);
    }
}
