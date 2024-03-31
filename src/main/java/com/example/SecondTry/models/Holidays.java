package com.example.SecondTry.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Holidays {

    @Id
    private Long id;
    private Short month;
    private int day;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(short month) {
        this.month = month;
    }
}
