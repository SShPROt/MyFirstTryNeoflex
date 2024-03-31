package com.example.SecondTry.service;

import com.example.SecondTry.constants.MyConstants;
import com.example.SecondTry.models.DaysInMonth;
import com.example.SecondTry.models.DaysInYear;
import com.example.SecondTry.models.Holidays;
import com.example.SecondTry.repos.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaysService {

    @Autowired
    HolidaysRepository holidaysRepository;

    public List<Holidays> findAll() {
        return holidaysRepository.findAll();
    }

    private int startVacationDay;
    private int endVacationDay;
    private int startVacationMonth;
    private int endVacationMonth;
    private int startVacationYear;
    private int endVacationYear;
    private int durationOfVacationDay = 0;
    private List<Holidays> holidays;

    public int calculateDurationOfVacation(String startDateVacation, String endDateVacation, List<DaysInYear> daysInYears, List<DaysInMonth> daysInMonths, List<Holidays> holidays) {
        int durationOfVacationYear, durationOfVacationMonth;
        String[] startVacation = startDateVacation.split("-");
        String[] endVacation = endDateVacation.split("-");
        startVacationDay = Integer.parseInt(startVacation[MyConstants.DAY]);
        endVacationDay = Integer.parseInt(endVacation[MyConstants.DAY]);
        startVacationMonth = Integer.parseInt(startVacation[MyConstants.MONTH]);
        endVacationMonth = Integer.parseInt(endVacation[MyConstants.MONTH]);
        startVacationYear = Integer.parseInt(startVacation[MyConstants.YEAR]);
        endVacationYear = Integer.parseInt(endVacation[MyConstants.YEAR]);
        this.holidays = holidays;
        durationOfVacationDay = 0;

        if ((startVacationYear > endVacationYear) ||
                (startVacationYear == endVacationYear && (startVacationMonth > endVacationMonth || (startVacationMonth == endVacationMonth && startVacationDay > endVacationDay)))) {
            System.out.println("Неправильная дата");
            return 0;
        }
        durationOfVacationYear = endVacationYear - startVacationYear;
        if (durationOfVacationYear > MyConstants.MAX_YEARS) return 0;

        durationOfVacationMonth = Math.abs(endVacationMonth - startVacationMonth);

        checkLeapYear();

        switch (durationOfVacationYear) {
            case 0: {
                switch (durationOfVacationMonth) {
                    case 0: {
                        durationOfVacationDay += endVacationDay - startVacationDay + 1;
                        removeHolidaysMini();
                        break;
                    }
                    default: {
                        for (int currentVacationMonth = startVacationMonth; currentVacationMonth < endVacationMonth - 1; currentVacationMonth++) {
                            durationOfVacationDay += daysInMonths.get(currentVacationMonth).getNumOfWorkDays();
                        }
                    }
                    case 1: {
                        durationOfVacationDay += daysInMonths.get(startVacationMonth - 1).getNumOfDays() - startVacationDay + 1;
                        durationOfVacationDay += endVacationDay;
                        removeHolidays();
                    }
                }
                break;
            }
            default: {

                for (int currentVacationYear = startVacationYear + 1; currentVacationYear < endVacationYear; currentVacationYear++) {
                    if (currentVacationYear % MyConstants.LEAP_YEAR_PERIOD == 0)
                        durationOfVacationDay += daysInYears.get(MyConstants.LEAP_YEAR).getNumOfWorkDays();
                    else durationOfVacationDay += daysInYears.get(MyConstants.NOT_LEAP_YEAR).getNumOfWorkDays();
                }
            }
            case 1: {
                int currentVacationMonth;
                for (currentVacationMonth = startVacationMonth; currentVacationMonth < MyConstants.MONTH_PER_YEAR; currentVacationMonth++) {
                    durationOfVacationDay += daysInMonths.get(currentVacationMonth).getNumOfWorkDays();
                }
                for (currentVacationMonth = endVacationMonth - 2; currentVacationMonth >= 0; currentVacationMonth--) {
                    durationOfVacationDay += daysInMonths.get(currentVacationMonth).getNumOfWorkDays();
                }

                durationOfVacationDay += daysInMonths.get(startVacationMonth - 1).getNumOfDays() - startVacationDay + 1;
                durationOfVacationDay += endVacationDay;

                removeHolidays();
                break;
            }
        }
        return durationOfVacationDay;
    }

    private void checkLeapYear() {
        if(durationOfVacationDay == 0) {
            if (startVacationYear % MyConstants.LEAP_YEAR_PERIOD == 0) {
                if (((startVacationMonth < MyConstants.NUM_OF_FEBRUARY_MONTH) || (startVacationMonth == MyConstants.NUM_OF_FEBRUARY_MONTH && startVacationDay <= MyConstants.LEAP_YEAR_DAY)) &&
                    ((endVacationMonth > MyConstants.NUM_OF_FEBRUARY_MONTH) || (endVacationMonth == MyConstants.NUM_OF_FEBRUARY_MONTH && endVacationDay == MyConstants.LEAP_YEAR_DAY)))
                    durationOfVacationDay++;
            }
            if((startVacationMonth == MyConstants.NUM_OF_FEBRUARY_MONTH && startVacationDay <= MyConstants.LEAP_YEAR_DAY) &&
                    (endVacationMonth == MyConstants.NUM_OF_FEBRUARY_MONTH && endVacationDay == MyConstants.LEAP_YEAR_DAY))
                durationOfVacationDay--;
        }
        else{
            if (startVacationYear % MyConstants.LEAP_YEAR_PERIOD == 0 || endVacationYear % MyConstants.LEAP_YEAR_PERIOD == 0) {
                if ((startVacationMonth < MyConstants.NUM_OF_FEBRUARY_MONTH) ||
                        (startVacationMonth == MyConstants.NUM_OF_FEBRUARY_MONTH && startVacationDay <= MyConstants.LEAP_YEAR_DAY))
                    durationOfVacationDay++;
                if ((endVacationMonth > MyConstants.NUM_OF_FEBRUARY_MONTH) ||
                        (endVacationMonth == MyConstants.NUM_OF_FEBRUARY_MONTH && endVacationDay == MyConstants.LEAP_YEAR_DAY))
                    durationOfVacationDay++;
            }
        }
    }

    private void removeHolidays() {
        for (Holidays holiday : holidays) {
            if (holiday.getMonth() == startVacationMonth) {
                if (holiday.getDay() >= startVacationDay) {
                    durationOfVacationDay--;
                }
            }
            if (holiday.getMonth() == endVacationMonth) {
                if (holiday.getDay() <= endVacationDay) {
                    durationOfVacationDay--;
                }
            }
        }
    }

    private void removeHolidaysMini() {
        for (Holidays holiday : holidays) {
            if (holiday.getMonth() == startVacationMonth) {
                if (holiday.getDay() >= startVacationDay && holiday.getDay() <= endVacationDay) {
                    durationOfVacationDay--;
                }
            }
        }
    }

    public int calculatePayment(String monthSalary, int durationOfVacation) {
        if (Integer.parseInt(monthSalary) > MyConstants.MAX_MONTH_SALARY) return 0;
        double dailySalary = Integer.parseInt(monthSalary) / MyConstants.AVERAGE_NUMBER_OF_WORKING_DAYS;
        return (int) dailySalary * durationOfVacation;
    }
}
