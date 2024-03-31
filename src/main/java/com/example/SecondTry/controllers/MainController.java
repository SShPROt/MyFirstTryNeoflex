package com.example.SecondTry.controllers;


import com.example.SecondTry.models.Holidays;
import com.example.SecondTry.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private HolidaysService holidaysService;

    @GetMapping(value = "/")
    public String calculate(@RequestParam(name = "salary", required = false, defaultValue = "0") String monthSalary,
                            @RequestParam(name = "startVacation", required = false, defaultValue = "2024-01-01") String startVacation,
                            @RequestParam(name = "endVacation", required = false, defaultValue = "2024-01-01") String endVacation,
                            Model model) {

        List<Holidays> holidays = holidaysService.findAll();

        long durationOfVacation = holidaysService.calculateDurationOfVacation(startVacation, endVacation, holidays);
        long result = holidaysService.calculatePayment(monthSalary, durationOfVacation);

        model.addAttribute("result", Long.toString(result));
        return "calculate";
    }
}