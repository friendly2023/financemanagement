package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.GeneralStatisticsResponse;
import com.kazimirov.financemanagement.dto.StatisticsOnDatesResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.enums.MonthName;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsOnDatesService {

    private OrderRepository orderRepository;

    @Autowired
    public StatisticsOnDatesService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<StatisticsOnDatesResponse> collectingStatisticsOnDates() {
        List<OrderEntity> completedOrderEntities = orderRepository.getAllCompletedOrders();

        return completedOrderEntities.stream()
                .collect(Collectors.groupingBy(
                        order -> Map.entry(order.getOrderDate().getYear(), order.getOrderDate().getMonthValue()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                groupedOrders -> new StatisticsOnDatesResponse(
                                        groupedOrders.get(0).getOrderDate().getYear(),
                                        MonthName.getMonthNameByNumber(groupedOrders.get(0).getOrderDate().getMonthValue()),
                                        groupedOrders.size(),
                                        groupedOrders.stream()
                                                .mapToInt(OrderEntity::getTotalProductPrice)
                                                .sum()
                                )
                        )
                ))
                .values()
                .stream()
                .sorted(Comparator.comparingInt(StatisticsOnDatesResponse::getYear).reversed()
                        .thenComparing(
                                (StatisticsOnDatesResponse response) -> MonthName.getMonthNumberByName(response.getMonth()),
                                Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }
}
