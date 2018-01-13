package com.example.doctormanagement.rest;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import com.example.doctormanagement.daoimplementation.JDBCDoctorDao;
import com.example.doctormanagement.daointerface.DoctorDao;
import com.example.doctormanagement.model.Doctor;
import com.example.doctormanagement.model.Patient;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {

    DoctorDao doctorDao = new JDBCDoctorDao(DatabaseData.MICROSOFT_DRIVER, DatabaseData.MICROSOFT_CONNECTION_URL);

    DoctorController() throws SQLException, ClassNotFoundException {}


    @GetMapping("/doctors/{doctorId}")
    public Doctor getById(@PathVariable(value="doctorId") String id) {
        return doctorDao.getDoctorById(Integer.parseInt(id));
        //example: http://localhost:8080/doctors/1
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDoctorByName")
    public Doctor getByNameSurname(@RequestParam(value="name", defaultValue="") String name,
                                   @RequestParam(value="surname", defaultValue="") String surname) {
        return doctorDao.getDoctorByNameSurname(name,surname);
        //example: http://localhost:8080/getDoctorByName?name=Anna&surname=Nowak
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addPatientToDoctor")
    public String addPatientToDoctor(@RequestParam(value="id", defaultValue="1") String doctorId,
                           @RequestBody Patient patient) {
        doctorDao.addPatient(Integer.parseInt(doctorId), patient);
        return "OK";
        //example: http://localhost:8080/addPatientToDoctor?id=1
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addDoctor")
    public String addDoctor(@RequestBody Doctor doctor) {
        doctorDao.addDoctor(doctor);
        return "OK";
        //example: http://localhost:8080/addDoctor
    }
}