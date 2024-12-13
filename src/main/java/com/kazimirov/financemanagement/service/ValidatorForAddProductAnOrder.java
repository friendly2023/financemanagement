package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidatorForAddProductAnOrder {
    private final ProductRepository productRepository;

    @Autowired
    public ValidatorForAddProductAnOrder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean validate(ProductEntity productEntity) {
        return productRepository.findByOrderEntityIsNull().stream()
                .anyMatch(product -> productEntity.getProductName().equalsIgnoreCase(product.getProductName()));
    }

}

