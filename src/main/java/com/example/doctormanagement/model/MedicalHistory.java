package com.example.doctormanagement.model;

public abstract class MedicalHistory {
    protected int id;
    protected Doctor doctor;
    protected Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getId() {
        return id;
    }
}
