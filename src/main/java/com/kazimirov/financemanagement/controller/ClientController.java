package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.model.ClientEntity;
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
        List<ClientResponse> clientResponses = clientService.getAllClientsById();
        model.addAttribute("clients", clientResponses);
        return "clients";
    }

    @GetMapping("/clients/new")
    public String showNewTaskForm() {
        return "new-client";
    }

    @PostMapping("/clients/new")
    public String createClient(ClientEntity clientEntity) {
        clientService.createClient(clientEntity);
        return "redirect:/";
    }
}
