package com.example.doctormanagement.daoimplementation;

import com.example.doctormanagement.daointerface.DoctorDao;
import com.example.doctormanagement.model.Doctor;
import com.example.doctormanagement.model.Patient;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JDBCDoctorDao implements DoctorDao {
    public boolean addPatient(int doctorId, Patient patient) {
        throw new NotImplementedException();
    }

    public Doctor getDoctorById(int id) {
        throw new NotImplementedException();
    }

    public Doctor getDoctorByNameSurname(String name, String surname) {
        throw new NotImplementedException();
    }
}
