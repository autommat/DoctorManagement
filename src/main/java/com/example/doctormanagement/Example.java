package com.example.doctormanagement;

import com.example.doctormanagement.daoimplementation.JDBCDoctorDao;
import com.example.doctormanagement.daoimplementation.JDBCPatientDao;
import com.example.doctormanagement.daointerface.DoctorDao;
import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Doctor;
import com.example.doctormanagement.model.Patient;

import java.sql.*;

public class Example {
    private static final Boolean USE_DROP_AND_CREATE = true;
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_DATABASE_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String MYSQL_DATABASE_USERNAME = "root";
    private static final String MYSQL_DATABASE_PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        if (USE_DROP_AND_CREATE) {
            dropAndCreateDatabase();
        }
        DoctorDao doctorDao = new JDBCDoctorDao(MYSQL_DRIVER, MYSQL_DATABASE_URL, MYSQL_DATABASE_USERNAME, MYSQL_DATABASE_PASSWORD);
        PatientDao patientDao = new JDBCPatientDao(MYSQL_DRIVER, MYSQL_DATABASE_URL, MYSQL_DATABASE_USERNAME, MYSQL_DATABASE_PASSWORD);

        Doctor doctorToAdd = new Doctor();
        doctorToAdd.setName("Anna");
        doctorToAdd.setSurname("Nowak");
        doctorToAdd.setSpecialization("Kardiolog");

        doctorDao.addDoctor(doctorToAdd);

        Doctor doctorFromDB = doctorDao.getDoctorByNameSurname("Anna", "Nowak");
        System.out.println(doctorFromDB);

        Patient patientToAdd = new Patient();
        patientToAdd.setName("Jan");
        patientToAdd.setSurname("Kowalski");
        patientToAdd.setBirthDate(new Date(new java.util.Date().getTime()));
        if (!doctorDao.addPatient(doctorFromDB.getId(), patientToAdd)) {
            throw new SQLException();
        }
        ;
        Patient patientFromDB = patientDao.getPatientById(1);
        System.out.println(patientFromDB);
    }

    private static void dropAndCreateDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);
        Connection connection = DriverManager.getConnection(MYSQL_DATABASE_URL, MYSQL_DATABASE_USERNAME, MYSQL_DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("drop table if exists PATIENTS");
        statement.execute("drop table if exists DOCTORS");
        statement.execute("create table DOCTORS (\n" +
                "       id integer not null auto_increment,\n" +
                "        name varchar(255),\n" +
                "        specialization varchar(255),\n" +
                "        surname varchar(255),\n" +
                "        primary key (id)\n" +
                "    )");
        statement.execute("create table PATIENTS (\n" +
                "       id integer not null auto_increment,\n" +
                "       docId integer ,\n" +
                "        name varchar(255),\n" +
                "        birthDate date,\n" +
                "        surname varchar(255),\n" +
                "        primary key (id),\n" +
                "        foreign key (docId) references DOCTORS(id)\n" +
                "    )");

        statement.close();
    }
}
