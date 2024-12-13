package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class ProductsController {

    private final OrderService orderService;

    @Autowired
    public ProductsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/product/new")
    public String showNewProductForm(Model model) {

        List<OrderResponse> orderEntities = orderService.getAllOrdersSortedByDueDate();
        model.addAttribute("orders", orderEntities);
        model.addAttribute("products", new ProductEntity());

        return "new-product";
    }

}
