package com.example.doctormanagement.model;

public abstract class MedicalHistory {
    protected int patientId;
    protected int id;


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
