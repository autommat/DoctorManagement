package com.example.doctormanagement.daoimplementation;

import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Prescription;
import com.example.doctormanagement.model.Treatment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JDBCPatientDao implements PatientDao{
    public Patient getPatientById(int id) {
        throw new NotImplementedException();
    }

    public boolean addTreatment(int patientId, Treatment treatment) {
        throw new NotImplementedException();
    }

    public Patient getPatientByNameSurname(String name, String surname) {
        throw new NotImplementedException();
    }

    public boolean addPrescription(int patientId, Prescription prescription) {
        throw new NotImplementedException();
    }
}
