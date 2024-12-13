package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private ValidatorForAddProductAnOrder validatorForAddProductAnOrder;

    @Autowired
    public ProductService(ProductRepository productRepository, ValidatorForAddProductAnOrder validatorForAddProductAnOrder) {
        this.productRepository = productRepository;
        this.validatorForAddProductAnOrder = validatorForAddProductAnOrder;
    }

    public ProductEntity createProduct(ProductEntity productEntity) {
        if (!validatorForAddProductAnOrder.validate(productEntity)) {
            return productRepository.save(productEntity);
        } else {
            //todo вывести сообщение об ошибке
            throw new IllegalArgumentException("Err: Товар уже в списке");
        }
    }

    public List<ProductEntity> getProductsWithoutOrder() {
        return productRepository.findByOrderEntityIsNull();
    }
}
