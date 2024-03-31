package com.example.SecondTry.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DaysInMonth {
    @Id
    private Long id;
    private int month, numOfDays, numOfWorkDays;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getNumOfWorkDays() {
        return numOfWorkDays;
    }

    public void setNumOfWorkDays(int numOfWorkDays) {
        this.numOfWorkDays = numOfWorkDays;
    }
}
