package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.GeneralStatisticsResponse;
import com.kazimirov.financemanagement.dto.ProductStatistics;
import com.kazimirov.financemanagement.dto.StatisticsOnDatesResponse;
import com.kazimirov.financemanagement.dto.YearSummary;
import com.kazimirov.financemanagement.service.GeneralStatisticsService;
import com.kazimirov.financemanagement.service.StatisticsOnDatesService;
import com.kazimirov.financemanagement.service.StatisticsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class StatisticsController {

    private GeneralStatisticsService generalStatisticsService;
    private StatisticsOnDatesService statisticsOnDatesService;
    private StatisticsProductService statisticsProductService;

    @Autowired
    public StatisticsController(GeneralStatisticsService generalStatisticsService,
                                StatisticsOnDatesService statisticsOnDatesService,
                                StatisticsProductService statisticsProductService) {
        this.generalStatisticsService = generalStatisticsService;
        this.statisticsOnDatesService = statisticsOnDatesService;
        this.statisticsProductService = statisticsProductService;
    }

    @GetMapping("/stats")
    public String showStatistics(Model model) {
        GeneralStatisticsResponse statistics = generalStatisticsService.collectingGeneralStatistics();
        model.addAttribute("statistics", statistics);
        return "general-statistics";
    }

    @GetMapping("/stats/on_dates")
    public String showStatisticsOnDates(Model model) {

        Map<Integer, List<StatisticsOnDatesResponse>> groupedByYear = statisticsOnDatesService.groupingOrdersByYear();
        Map<Integer, YearSummary> statisticsByYear = statisticsOnDatesService.calculatingStatisticsByYear(groupedByYear);

        model.addAttribute("monthlyReports", groupedByYear);
        model.addAttribute("statisticsByYear", statisticsByYear);
        return "statistics-on-dates";
    }

    @GetMapping("/stats/products")
    public String showStatisticsProducts(Model model) {

        List<ProductStatistics> productStatistics = statisticsProductService.calculateProductStatistics();

        model.addAttribute("productStatistics", productStatistics);
        return "statistics-on-products";
    }
}
