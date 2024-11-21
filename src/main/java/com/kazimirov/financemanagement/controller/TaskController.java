package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.model.Task;
import com.kazimirov.financemanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam String dueDate) {
        LocalDate dueDateParsed = LocalDate.parse(dueDate);
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDateParsed);
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id).orElse(null);
    }

}
