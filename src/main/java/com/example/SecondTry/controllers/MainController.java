package com.example.SecondTry.controllers;


import com.example.SecondTry.models.DaysInYear;
import com.example.SecondTry.models.Holidays;
import com.example.SecondTry.models.DaysInMonth;
import com.example.SecondTry.service.DaysInMonthService;
import com.example.SecondTry.service.DaysInYearService;
import com.example.SecondTry.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class MainController {

    @Autowired
    private HolidaysService holidaysService;

    @Autowired
    private DaysInMonthService daysInMonthService;

    @Autowired
    private DaysInYearService daysInYearService;

    @GetMapping(value = "/")
    public String calculate(@RequestParam(name = "salary", required = false, defaultValue = "999999") String monthSalary,
                            @RequestParam(name = "startVacation", required = false, defaultValue = "2024-01-01") String startVacation,
                            @RequestParam(name = "endVacation", required = false, defaultValue = "2024-02-29") String endVacation,
                            Model model) {

        List<DaysInMonth> daysInMonth = daysInMonthService.findAll();
        List<DaysInYear> daysInYear = daysInYearService.findAll();
        List<Holidays> holidays = holidaysService.findAll();

        int durationOfVacation = holidaysService.calculateDurationOfVacation(startVacation, endVacation,daysInYear, daysInMonth, holidays);
        int result = holidaysService.calculatePayment(monthSalary, durationOfVacation);

        model.addAttribute("result", Integer.toString(result));
        return "calculate";
    }
}