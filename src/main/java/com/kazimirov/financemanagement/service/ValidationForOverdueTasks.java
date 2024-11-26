package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Task;
import com.kazimirov.financemanagement.model.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidationForOverdueTasks {

    public void validate(Task task) {
        if (isTaskOverdue(task)) {
            handleOverdueTask(task);
        }
    }

    private boolean isTaskOverdue(Task task) {
        return task.getOrderDate().isAfter(task.getDueDate());
    }

    private void handleOverdueTask(Task task) {
        // TODO обработать вывод ошибки на веб-страницу
        if (task.getStatus() == TaskStatus.ONGOING) {
            throw new IllegalArgumentException("Невозможно данной задаче присвоить статус 'ONGOING'");
        }
    }
}
