package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderTotalCalculator {

    ProductService productService;

    @Autowired
    public OrderTotalCalculator(ProductService productService) {
        this.productService = productService;
    }

    public int calculateTotalNewOrder(List<String> productNames, List<Integer> quantities) {
        int totalProductPrice = 0;

        for (int i = 0; i < productNames.size(); i++) {
            ProductEntity product = productService.getByName(productNames.get(i));

            totalProductPrice += quantities.get(i) * product.getPrice();
        }

        return totalProductPrice;
    }
}
