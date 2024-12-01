package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String showOrderList(Model model) {
        List<Order> orders = orderService.getAllOrdersSortedByDueDateDesc();
        model.addAttribute("orders", orders);
        return "order"; // имя шаблона HTML
    }

    @GetMapping("/orders/new")
    public String showNewTaskForm() {
        return "new-order"; // Имя шаблона для формы создания задачи
    }

    @PostMapping("/orders/new")
    public String createOrder(Order order) {
        orderService.createOrder(order); // Сохраняем задачу через сервис
        return "redirect:/"; // Возвращаемся на главную страницу после создания
    }


}
