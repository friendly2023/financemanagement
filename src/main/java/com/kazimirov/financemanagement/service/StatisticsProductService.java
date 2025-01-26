package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ProductStatistics;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsProductService {
    private OrderRepository orderRepository;

    @Autowired
    public StatisticsProductService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ProductStatistics> calculateProductStatistics() {

        List<ProductEntity> allCompletedProduct = orderRepository.getAllCompletedProduct();

        int totalEarnings = calculatingTotalEarnings(allCompletedProduct);

        Map<String, ProductStatistics> statisticsMap = allCompletedProduct.stream()
                .collect(Collectors.groupingBy(
                        ProductEntity::getProductName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    int totalQuantity = list.stream()
                                            .mapToInt(ProductEntity::getQuantity)
                                            .sum();

                                    int totalPrice = list.stream()
                                            .mapToInt(product -> product.getQuantity() * product.getPrice())
                                            .sum();

                                    double percentageOfTotalEarnings = ((double) totalPrice / totalEarnings) * 100;
                                    double roundedPercentageOfTotalEarnings = Math.round(percentageOfTotalEarnings * 100.0) / 100.0;

                                    return new ProductStatistics(
                                            list.get(0).getProductName(),
                                            totalQuantity,
                                            totalPrice,
                                            roundedPercentageOfTotalEarnings
                                    );
                                }
                        )
                ));

        List<ProductStatistics> result = new ArrayList<>(statisticsMap.values());
        result.sort(Comparator.comparingInt(ProductStatistics::getQuantitySold).reversed());

        return result;
    }

    private int calculatingTotalEarnings(List<ProductEntity> allCompletedProduct) {

        return allCompletedProduct.stream()
                .mapToInt(product -> product.getQuantity() * product.getPrice())
                .sum();
    }
}
