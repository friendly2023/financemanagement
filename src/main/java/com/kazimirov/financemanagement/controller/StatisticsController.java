package com.kazimirov.financemanagement.controller;

import com.kazimirov.financemanagement.dto.GeneralStatisticsResponse;
import com.kazimirov.financemanagement.service.GeneralStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

    private GeneralStatisticsService generalStatisticsService;

    @Autowired
    public StatisticsController(GeneralStatisticsService generalStatisticsService) {
        this.generalStatisticsService = generalStatisticsService;
    }

    @GetMapping("/stats")
    public String showStatistics(Model model) {
        GeneralStatisticsResponse statistics = generalStatisticsService.collectingGeneralStatistics();
        model.addAttribute("statistics", statistics);
        return "general-statistics";
    }
}
