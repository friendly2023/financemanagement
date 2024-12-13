package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.ClientResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/clients";
    }

    @PostMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/client={clientId}_order={orderId}")
    public String editClient(
            @PathVariable Long clientId,
            @PathVariable Long orderId,
            Model model) {

        ClientEntity client = clientService.getClientById(clientId);

        model.addAttribute("client", client);
        model.addAttribute("orderId", orderId);

        return "client-editing";
    }

    @PostMapping("/clients/edit/client={clientId}_order={orderId}")
    public String saveEditOrder(@PathVariable Long clientId,
                                @PathVariable Long orderId,
                                ClientEntity clientEntity) {

        ClientEntity existingClient = clientService.getClientById(clientId);

        existingClient.setName(clientEntity.getName());
        existingClient.setLinkToProfile(clientEntity.getLinkToProfile());
        existingClient.setNote(clientEntity.getNote());

        clientService.createClient(existingClient);

        return "redirect:/orders/more/" + orderId;
    }
}
