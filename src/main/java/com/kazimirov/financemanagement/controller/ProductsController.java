package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.dto.ProductResponse;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.service.OrderService;
import com.kazimirov.financemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class ProductsController {

    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public ProductsController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/product/new")
    public String showNewProductForm(Model model) {

        List<OrderResponse> orderEntities = orderService.getAllOrdersSortedByDueDate();
        model.addAttribute("orders", orderEntities);
        model.addAttribute("products", new ProductEntity());

        return "new-product";
    }

    @PostMapping("/product/new")
    public String createProduct(ProductEntity productEntity) {
        productService.createProduct(productEntity);

        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProductsList(Model model) {
        List<ProductResponse> productResponses = productService.getProducts();
        model.addAttribute("products", productResponses);

        return "products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
       productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            Model model) {

        ProductEntity productEntity = productService.getProductById(id);

        model.addAttribute("product", productEntity);

        return "product-editing";
    }

    @PostMapping("/products/edit/{id}")
    public String saveEditProduct(ProductEntity productEntity, @PathVariable Long id) {

        productService.editProduct(productEntity, id);

        return "redirect:/products";
    }
}
