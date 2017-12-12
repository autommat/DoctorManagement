package com.example.doctormanagement.model;

import com.example.doctormanagement.model.MedicalHistory;

import java.util.Date;

public class Treatment extends MedicalHistory {
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
