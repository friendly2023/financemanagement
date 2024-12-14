package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.dto.ProductResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.service.ClientService;
import com.kazimirov.financemanagement.service.OrderService;
import com.kazimirov.financemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService,
                           ClientService clientService,
                           ProductService productService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String showOrdersList(Model model) {
        List<OrderResponse> orders = orderService.getAllOrdersSortedByDueDate();
        model.addAttribute("orders", orders);
        return "orders"; // имя шаблона HTML
    }

    @GetMapping("/orders/new")
    public String showNewOrderForm(Model model) {
        List<ClientEntity> clientEntities = clientService.getAllClients();
        List<ProductResponse> products = productService.getProducts();

        model.addAttribute("clients", clientEntities);
        model.addAttribute("products", products);
        model.addAttribute("order", new OrderEntity());

        return "new-order";
    }

    @PostMapping("/orders/new")
    public String createOrder(OrderEntity orderEntity,
                              @RequestParam("clientId") Long clientId,
                              @RequestParam("products[]") List<String> productNames) {

        ClientEntity clientEntity = clientService.getClientById(clientId);
        orderEntity.setClient(clientEntity);

        List<ProductEntity> products = new ArrayList<>();
        for (String productName : productNames) {
            ProductEntity product = productService.getByName(productName);
            products.add(product);
        }

        orderEntity.setProductEntities(products);
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
        orderService.deleteOrder(id);
        return "redirect:/";
    }

    @GetMapping("/client/orders/{id}")
    public String displayingClientOrders(@PathVariable Long id, Model model) {
        List<OrderResponse> orders = orderService.getForClientOrdersByIdSortedByDueDate(id);
        ClientEntity clientEntity = clientService.getClientById(id);
        model.addAttribute("orders", orders);
        model.addAttribute("client", clientEntity);
        return "client-orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        OrderEntity order = orderService.searchOrderById(id);
        model.addAttribute("order", order);
        return "order-editing";
    }

    @PostMapping("/orders/edit/{id}")
    public String saveEditOrder(OrderEntity orderEntity, @PathVariable Long id) {

        OrderEntity existingOrder = orderService.searchOrderById(id);

        existingOrder.setNote(orderEntity.getNote());
        existingOrder.setOrderDate(orderEntity.getOrderDate());
        existingOrder.setDueDate(orderEntity.getDueDate());
        existingOrder.setCity(orderEntity.getCity());
        existingOrder.setTotalProductPrice(orderEntity.getTotalProductPrice());
        existingOrder.setStatus(orderEntity.getStatus());

        orderService.createOrder(existingOrder);

        return "redirect:/orders/more/{id}";
    }
}
