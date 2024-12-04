package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.OrderDTO;
import com.kazimirov.financemanagement.model.Client;
import com.kazimirov.financemanagement.model.Order;
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
        List<OrderDTO> orders = orderService.getAllOrdersSortedByDueDate();
        model.addAttribute("orders", orders);
        return "order"; // имя шаблона HTML
    }

    @GetMapping("/orders/new")
    public String showNewOrderForm(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("order", new Order());

        return "new-order"; // Имя шаблона для формы создания задачи
    }

    @PostMapping("/orders/new")
    public String createOrder(Order order, @RequestParam("clientId") Long clientId) {
        Client client = clientService.getClientById(clientId);
        order.setClient(client);
        orderService.createOrder(order);

        return "redirect:/";
    }

    @GetMapping("/orders/more/{id}")
    public String getOrderDetails(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("client", order.getClient());
        return "order-details";
    }

}
