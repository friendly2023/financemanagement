package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.GeneralStatisticsResponse;
import com.kazimirov.financemanagement.dto.StatisticsOnDatesResponse;
import com.kazimirov.financemanagement.dto.YearSummary;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.enums.MonthName;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public Map<Integer, List<StatisticsOnDatesResponse>> groupingOrdersByYear(){
        List<StatisticsOnDatesResponse> statistics = this.collectingStatisticsOnDates();

        Map<Integer, List<StatisticsOnDatesResponse>> groupedByYear = statistics.stream()
                .collect(Collectors.groupingBy(StatisticsOnDatesResponse::getYear));

        return groupedByYear.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<Integer, YearSummary> calculatingStatisticsByYear(Map<Integer, List<StatisticsOnDatesResponse>> groupedByYear) {
        Map<Integer, YearSummary> yearSummaries = new HashMap<>();

        groupedByYear.forEach((year, reports) -> {
            int totalOrders = reports.stream().mapToInt(StatisticsOnDatesResponse::getTotalOrdersSold).sum();
            double totalEarnings = reports.stream().mapToDouble(StatisticsOnDatesResponse::getTotalEarnings).sum();
            yearSummaries.put(year, new YearSummary(totalOrders, totalEarnings));
        });

        return yearSummaries;
    }
}
