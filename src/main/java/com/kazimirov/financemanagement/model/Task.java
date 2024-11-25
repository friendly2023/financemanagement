package com.kazimirov.financemanagement.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TaskStatus status;

    @PrePersist
    public void validate() {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalStateException("Отсутствует заголовок задачи");
        }
        if (dueDate == null) {
            throw new IllegalStateException("Отсутствует дедлайн задачи");
        }
    }

    public Task() {
        this.orderDate = LocalDate.now();
    }

    public Task(String title, String description, LocalDate orderDate, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    @Transient // Поле не сохраняется в базе данных
    public long getDaysLeft() {

        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

}
