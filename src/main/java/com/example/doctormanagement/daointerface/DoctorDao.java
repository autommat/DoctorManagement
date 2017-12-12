package com.example.doctormanagement.daointerface;

import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Doctor;

public interface DoctorDao {
    boolean addPatient(int doctorId, Patient patient);
    Doctor getDoctorById(int id);
    Doctor getDoctorByNameSurname(String name, String surname);
}
