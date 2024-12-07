package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.model.ClientEntity;
import com.kazimirov.financemanagement.model.OrderEntity;
import com.kazimirov.financemanagement.service.ClientService;
import com.kazimirov.financemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String showOrderList(Model model) {
        List<OrderResponse> orders = orderService.getAllOrdersSortedByDueDate();
        model.addAttribute("orders", orders);
        return "orders"; // имя шаблона HTML
    }

    @GetMapping("/orders/new")
    public String showNewOrderForm(Model model) {
        List<ClientEntity> clientEntities = clientService.getAllClients();
        model.addAttribute("clients", clientEntities);
        model.addAttribute("order", new OrderEntity());

        return "new-order"; // Имя шаблона для формы создания задачи
    }

    @PostMapping("/orders/new")
    public String createOrder(OrderEntity orderEntity, @RequestParam("clientId") Long clientId) {
        ClientEntity clientEntity = clientService.getClientById(clientId);
        orderEntity.setClient(clientEntity);
        orderService.createOrder(orderEntity);

        return "redirect:/";
    }

    @GetMapping("/orders/more/{id}")
    public String getOrderDetails(@PathVariable Long id, Model model) {
        OrderEntity orderEntity = orderService.getOrderById(id).get();
        model.addAttribute("order", orderEntity);
        model.addAttribute("client", orderEntity.getClient());
        return "order-details";
    }

    @PostMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);  // Удаление заказа через сервис
        return "redirect:/";  // Перенаправляем на страницу с заказами
    }

}
