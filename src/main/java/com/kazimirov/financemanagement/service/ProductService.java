package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ProductResponse;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ProductEntity addProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public List<ProductResponse> getProducts() {

        return getProductsWithoutOrder().stream()
                .map(ProductResponseFactory::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductEntity> getProductsWithoutOrder() {
        return productRepository.findByOrderEntityIsNull();
    }

    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
    }

    public ProductEntity getByName(String productName) {
        List<ProductEntity> productEntityList = getProductsWithoutOrder();

        return productEntityList.stream()
                .filter(product -> product.getProductName().equals(productName))
                .findFirst()
                .get();
    }

    public ProductEntity getProductById(Long productId) {
        return productRepository.findById(productId)
                .get();
    }
}