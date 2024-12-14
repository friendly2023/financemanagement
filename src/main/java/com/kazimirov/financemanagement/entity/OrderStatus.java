package com.kazimirov.financemanagement.entity;

public enum OrderStatus {
    ONGOING("В работе"),  // В работе
    COMPLETED("Завершено"), // Завершено
    CANCELLED("Отменено"), // Отменено
    OVERDUE("Просрочено");  // Просрочено

    private final String description;

    // Конструктор для инициализации описания
    OrderStatus(String description) {
        this.description = description;
    }

    // Метод для получения описания
    public String getDescription() {
        return description;
    }
}
