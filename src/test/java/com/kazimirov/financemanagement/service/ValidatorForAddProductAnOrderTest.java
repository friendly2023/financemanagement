package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorForAddProductAnOrderTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ValidatorForAddProductAnOrder validatorForAddProductAnOrder;

    @Test
    void validate_ProductExists_true() {
        ProductEntity productEntityToValidate = new ProductEntity();
        productEntityToValidate.setProductName("Test Product");

        ProductEntity existingProduct = new ProductEntity();
        existingProduct.setProductName("Test Product");

        when(productRepository.findByOrderEntityIsNullOrderByProductName()).thenReturn(List.of(existingProduct));

        boolean result = validatorForAddProductAnOrder.validate(productEntityToValidate);

        assertTrue(result);

        verify(productRepository).findByOrderEntityIsNullOrderByProductName();
    }

    @Test
    void validate_ProductNotExists_false() {
        ProductEntity productEntityToValidate = new ProductEntity();
        productEntityToValidate.setProductName("Non-existent Product");

        when(productRepository.findByOrderEntityIsNullOrderByProductName()).thenReturn(List.of());

        boolean result = validatorForAddProductAnOrder.validate(productEntityToValidate);

        assertFalse(result);

        verify(productRepository).findByOrderEntityIsNullOrderByProductName();
    }
}