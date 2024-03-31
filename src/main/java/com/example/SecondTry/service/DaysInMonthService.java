package com.example.SecondTry.service;

import com.example.SecondTry.models.DaysInMonth;
import com.example.SecondTry.repos.DaysInMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DaysInMonthService {
    @Autowired
    DaysInMonthRepository daysInMonthRepository;
    public List<DaysInMonth> findAll(){
        return daysInMonthRepository.findAll();
    }
}
