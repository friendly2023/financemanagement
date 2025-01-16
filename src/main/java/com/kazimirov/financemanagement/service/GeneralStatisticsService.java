package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.GeneralStatisticsResponse;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneralStatisticsService {

    private final OrderRepository orderRepository;

    public GeneralStatisticsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public GeneralStatisticsResponse collectingGeneralStatistics() {

        Integer quantityAllOrders = orderRepository.countAllOrders();

        Integer quantityAllCompletedOrders = orderRepository.countAllCompletedOrders();
        Integer quantityAllOngoingOrders = orderRepository.countAllOngoingOrders();
        Integer quantityAllCancelledOrders = orderRepository.countAllCancelledOrders();
        Integer quantityAllOverdueOrders = orderRepository.countAllOverdueOrders();

        Integer quantityTotalProductSold = orderRepository.getTotalProductSold();
        Integer quantityTotalEarnings = orderRepository.getTotalEarnings();

        return new GeneralStatisticsResponse(
                (quantityAllOrders != null) ? quantityAllOrders : 0,
                (quantityAllCompletedOrders != null) ? quantityAllCompletedOrders : 0,
                (quantityAllOngoingOrders != null) ? quantityAllOngoingOrders : 0,
                (quantityAllCancelledOrders != null) ? quantityAllCancelledOrders : 0,
                (quantityAllOverdueOrders != null) ? quantityAllOverdueOrders : 0,
                (quantityAllCompletedOrders != null && quantityAllCompletedOrders != 0) ? Math.round(quantityTotalEarnings / quantityAllCompletedOrders) : 0,
                (quantityTotalProductSold != null) ? quantityTotalProductSold : 0,
                (quantityTotalEarnings != null) ? quantityTotalEarnings : 0
        );
    }
}
