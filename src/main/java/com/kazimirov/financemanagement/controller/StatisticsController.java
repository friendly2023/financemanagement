package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.GeneralStatisticsResponse;
import com.kazimirov.financemanagement.dto.StatisticsOnDatesResponse;
import com.kazimirov.financemanagement.service.GeneralStatisticsService;
import com.kazimirov.financemanagement.service.StatisticsOnDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {

    private GeneralStatisticsService generalStatisticsService;
    private StatisticsOnDatesService statisticsOnDatesService;

    @Autowired
    public StatisticsController(GeneralStatisticsService generalStatisticsService,
                                StatisticsOnDatesService statisticsOnDatesService) {
        this.generalStatisticsService = generalStatisticsService;
        this.statisticsOnDatesService = statisticsOnDatesService;
    }

    @GetMapping("/stats")
    public String showStatistics(Model model) {
        GeneralStatisticsResponse statistics = generalStatisticsService.collectingGeneralStatistics();
        model.addAttribute("statistics", statistics);
        return "general-statistics";
    }

    @GetMapping("/stats/on_dates")
    public String showStatisticsOnDates(Model model) {
        List<StatisticsOnDatesResponse> statistics = statisticsOnDatesService.collectingStatisticsOnDates();
        model.addAttribute("monthlyReports", statistics);
        return "statistics-on-dates";
    }
}
