package com.example.doctormanagement.model;

import java.sql.Date;

public class Prescription extends MedicalHistory {
    private float dosageMg;
    private int dosageADay;
    private String medicine;
    Date date;

    public float getDosageMg() {
        return dosageMg;
    }

    public void setDosageMg(float dosageMg) {
        this.dosageMg = dosageMg;
    }

    public int getDosageADay() {
        return dosageADay;
    }

    public void setDosageADay(int dosageADay) {
        this.dosageADay = dosageADay;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
