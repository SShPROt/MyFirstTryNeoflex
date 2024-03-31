package com.example.SecondTry.models;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DaysInYear {
    @Id
    private Long id;
    private int numOfDays, numOfWorkDays;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
