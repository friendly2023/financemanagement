package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.model.Task;
import com.kazimirov.financemanagement.model.TaskStatus;
import com.kazimirov.financemanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showTaskList(Model model) {
        List<Task> tasks = taskService.getTaskByStatus(TaskStatus.ONGOING); // Пример фильтрации
        model.addAttribute("tasks", tasks);
        return "task"; // имя вашего шаблона HTML
    }
}
