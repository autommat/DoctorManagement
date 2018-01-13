package com.example.doctormanagement.rest;

import com.example.doctormanagement.daoimplementation.JDBCPatientDao;
import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Doctor;
import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Prescription;
import com.example.doctormanagement.model.Treatment;
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

    @RequestMapping(method = RequestMethod.POST, value = "/addTreatment")
    public String addTreatment(@RequestParam(value = "id", defaultValue = "1") String patientId,
                               @RequestBody Treatment treatment) {
        patientDao.addTreatment(Integer.parseInt(patientId), treatment);
        return "OK";
        //example: http://localhost:8080/addTreatment
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTreatment")
    public String addPrescription(@RequestParam(value = "id", defaultValue = "1") String patientId,
                                  @RequestBody Prescription prescription) {
        patientDao.addPrescription(Integer.parseInt(patientId), prescription);
        return "OK";
        //example: http://localhost:8080/addPrescription
    }
}