
package com.example.SecondTry.controllers;

import com.example.SecondTry.models.DaysInYear;
import com.example.SecondTry.models.Holidays;
import com.example.SecondTry.models.DaysInMonth;
import com.example.SecondTry.service.DaysInMonthService;
import com.example.SecondTry.service.DaysInYearService;
import com.example.SecondTry.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReserveController {

    @Autowired
    private HolidaysService holidaysService;

    @Autowired
    private DaysInMonthService daysInMonthService;

    @Autowired
    private DaysInYearService daysInYearService;

    @GetMapping("/calculate")
    public ResponseEntity<String> calculate2(@RequestParam(name = "salary", required = false, defaultValue = "999999") String monthSalary,
                             @RequestParam(name = "startVacation", required = false, defaultValue = "2024-01-01") String startVacation,
                             @RequestParam(name = "endVacation", required = false, defaultValue = "2024-02-29") String endVacation) {

        List<DaysInMonth> daysInMonth = daysInMonthService.findAll();
        List<DaysInYear> daysInYear = daysInYearService.findAll();
        List<Holidays> holidays = holidaysService.findAll();

        int durationOfVacation = holidaysService.calculateDurationOfVacation(startVacation, endVacation,daysInYear, daysInMonth, holidays);
        int result = holidaysService.calculatePayment(monthSalary, durationOfVacation);
        return new ResponseEntity<> (Integer.toString(result), HttpStatus.OK);
    }
}