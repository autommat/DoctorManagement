package com.example.doctormanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private List<Patient> patients = new ArrayList<Patient>();
    private String specialization;
    private String name;
    private String surname;

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
