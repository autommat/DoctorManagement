package com.example.doctormanagement.model;

public abstract class MedicalHistory {
    protected int id;
    protected Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public int getId() {
        return id;
    }
}
