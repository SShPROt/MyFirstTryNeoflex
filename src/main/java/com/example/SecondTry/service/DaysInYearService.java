package com.example.SecondTry.service;

import com.example.SecondTry.models.DaysInYear;
import com.example.SecondTry.repos.DaysInYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DaysInYearService {
    @Autowired
    DaysInYearRepository daysInYearRepository;
    public List<DaysInYear> findAll(){
        return daysInYearRepository.findAll();
    }
}
