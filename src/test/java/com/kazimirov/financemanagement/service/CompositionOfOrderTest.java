package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompositionOfOrderTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CompositionOfOrder compositionOfOrder;

    private ProductEntity productEntity;
    private OrderEntity orderEntity;

    @Test
    void creatCompositionOfOrder() {
        orderEntity = new OrderEntity();

        productEntity = new ProductEntity();
        productEntity.setProductName("ProductName");
        productEntity.setPrice(100);
        productEntity.setQuantity(2);

        List<ProductEntity> productEntities = Collections.singletonList(productEntity);

        when(orderRepository.findAllProductsByOrderId(orderEntity.getId())).thenReturn(productEntities);

        String compositionOfOrderText = compositionOfOrder.creatDetailedCompositionOfOrder(orderEntity);

        String compositionOfOrderTextFromDB = "1. ProductName - 100 р/шт. - 2 шт. - Итого: 200 р\n";

        assertEquals(compositionOfOrderTextFromDB, compositionOfOrderText);
    }
}