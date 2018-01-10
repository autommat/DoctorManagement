package com.example.doctormanagement.rest;

import com.example.doctormanagement.daoimplementation.JDBCPatientDao;
import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class PatientController {
    PatientDao patientDao = new JDBCPatientDao(DatabaseData.MICROSOFT_DRIVER, DatabaseData.MICROSOFT_CONNECTION_URL);

    PatientController() throws SQLException, ClassNotFoundException {
    }

    @GetMapping("/patients/{patientId}")
    public Patient getById(@PathVariable(value = "patientId") String id) {
        return patientDao.getPatientById(Integer.parseInt(id));
        //example: http://localhost:8080/patients/1
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getPatientByName")
    public Patient getByNameSurname(@RequestParam(value = "name", defaultValue = "") String name,
                                    @RequestParam(value = "surname", defaultValue = "") String surname) {
        return patientDao.getPatientByNameSurname(name, surname);
        //example: http://localhost:8080/getPatientByName?name=Jan&surname=Kowalski
    }
}