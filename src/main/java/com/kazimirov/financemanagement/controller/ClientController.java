package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.ClientDTO;
import com.kazimirov.financemanagement.dto.OrderDTO;
import com.kazimirov.financemanagement.model.Client;
import com.kazimirov.financemanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String showClientsList(Model model) {
        List<ClientDTO> clients = clientService.getAllClientsById();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/clients/new")
    public String showNewTaskForm() {
        return "new-client";
    }

    @PostMapping("/clients/new")
    public String createOrder(Client client) {
        clientService.createClient(client);
        return "redirect:/";
    }
}
