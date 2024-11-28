package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Task;
import org.springframework.stereotype.Component;

@Component
public class ValidationForVerifyDescription {

    public void validate(Task task) {
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            task.setDescription("*описание отсутствует*");
        }
    }
}
