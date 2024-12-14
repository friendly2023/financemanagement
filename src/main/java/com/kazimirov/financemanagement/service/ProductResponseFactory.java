package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ProductResponse;
import com.kazimirov.financemanagement.entity.ProductEntity;

public class ProductResponseFactory {

    static public ProductResponse mapToProductResponse(ProductEntity productEntity) {

        return new ProductResponse(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getPrice(),
                productEntity.getNote()
        );
    }
}
