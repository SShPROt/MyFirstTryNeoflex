package com.example.SecondTry.service;

import com.example.SecondTry.constants.MyConstants;
import com.example.SecondTry.models.Holidays;
import com.example.SecondTry.repos.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HolidaysService {

    @Autowired
    HolidaysRepository holidaysRepository;

    public List<Holidays> findAll() {
        return holidaysRepository.findAll();
    }

    public long calculateDurationOfVacation(String startDateVacation, String endDateVacation, List<Holidays> holidays) {
        String[] startVacation = startDateVacation.split("-");
        String[] endVacation = endDateVacation.split("-");
        short startVacationDay = Short.parseShort(startVacation[MyConstants.DAY]);
        short endVacationDay = Short.parseShort(endVacation[MyConstants.DAY]);
        short startVacationMonth = Short.parseShort(startVacation[MyConstants.MONTH]);
        short endVacationMonth = Short.parseShort(endVacation[MyConstants.MONTH]);
        short startVacationYear = Short.parseShort(startVacation[MyConstants.YEAR]);
        short endVacationYear = Short.parseShort(endVacation[MyConstants.YEAR]);
        long durationOfVacation;

        LocalDate startDate = LocalDate.of(startVacationYear, startVacationMonth, startVacationDay);
        LocalDate endDate = LocalDate.of(endVacationYear, endVacationMonth,endVacationDay);
        durationOfVacation = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        for(LocalDate currentDate = startDate; currentDate.isBefore(endDate.plusDays(1)); currentDate = currentDate.plusDays(1)){
            for (Holidays holiday : holidays) {
                if (currentDate.getMonthValue() == holiday.getMonth() & (currentDate.getDayOfMonth() == holiday.getDay()) ||
                        (currentDate.getDayOfWeek().toString().equals("SATURDAY"))||(currentDate.getDayOfWeek().toString().equals("SUNDAY"))) {
                    System.out.println(currentDate.getDayOfMonth() + " " + currentDate.getDayOfWeek() + " delete");
                    durationOfVacation--;
                    break;
                }
            }
        }
        return durationOfVacation;
    }

    public long calculatePayment(String monthSalary, long durationOfVacation) {
        if (Integer.parseInt(monthSalary) > MyConstants.MAX_MONTH_SALARY) return 0;
        double dailySalary = Integer.parseInt(monthSalary) / MyConstants.AVERAGE_NUMBER_OF_WORKING_DAYS;
        return (long) dailySalary * durationOfVacation;
    }
}
