package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidationForVerifyDescription {

    public void validate(OrderEntity orderEntity) {
        if (orderEntity.getNote() == null || orderEntity.getNote().trim().isEmpty()) {
            orderEntity.setNote("*примечание отсутствует*");
        }
    }
}
