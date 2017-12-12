package com.example.doctormanagement.daointerface;

import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Prescription;
import com.example.doctormanagement.model.Treatment;

public interface PatientDao {
    Patient getPatientById(int id);
    boolean addTreatment(int patientId, Treatment treatment);
    Patient getPatientByNameSurname(String name, String surname);
    boolean addPrescription(int patientId, Prescription prescription);
}
