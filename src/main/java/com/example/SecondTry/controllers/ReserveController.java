
package com.example.SecondTry.controllers;

import com.example.SecondTry.models.Holidays;
import com.example.SecondTry.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class ReserveController {

    @Autowired
    private HolidaysService holidaysService;

    @GetMapping("/calculate")
    public ResponseEntity<String> calculate2(@RequestParam(name = "salary", required = true) String monthSalary,
                                             @RequestParam(name = "startVacation", required = true) String startVacation,
                                             @RequestParam(name = "endVacation", required = true) String endVacation) {

        List<Holidays> holidays = holidaysService.findAll();

        long durationOfVacation = holidaysService.calculateDurationOfVacation(startVacation, endVacation, holidays);
        long result = holidaysService.calculatePayment(monthSalary, durationOfVacation);
        return new ResponseEntity<> (Long.toString(result), HttpStatus.OK);
    }
}