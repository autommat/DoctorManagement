package com.example.doctormanagement;

import com.example.doctormanagement.daoimplementation.JDBCDoctorDao;
import com.example.doctormanagement.daoimplementation.JDBCPatientDao;
import com.example.doctormanagement.daointerface.DoctorDao;
import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Doctor;
import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Prescription;
import com.example.doctormanagement.model.Treatment;

import java.sql.*;

public class Example {
    private static final Boolean USE_DROP_AND_CREATE = true;
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_DATABASE_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String MYSQL_DATABASE_USERNAME = "root";
    private static final String MYSQL_DATABASE_PASSWORD = "root";

    private static final String MICROSOFT_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String MICROSOFT_DATABASE_URL = "jdbc:sqlserver://miisu.database.windows.net:1433/HIS";
    private static final String MICROSOFT_DATABASE_USERNAME = "ServerAdmin@miisu";
    private static final String MICROSOFT_DATABASE_PASSWORD = "Ristolainen555";
    private static final String MICROSOFT_CONNECTION_URL = "jdbc:sqlserver://miisu.database.windows.net:1433" + ";" +
            "database=HIS" + ";" +
            "user=ServerAdmin@miisu" + ";" +
            "password=Ristolainen555";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        if (USE_DROP_AND_CREATE) {
            dropAndCreateDatabase();
        }
//        DoctorDao doctorDao = new JDBCDoctorDao(MYSQL_DRIVER, MYSQL_DATABASE_URL, MYSQL_DATABASE_USERNAME, MYSQL_DATABASE_PASSWORD);
//        PatientDao patientDao = new JDBCPatientDao(MYSQL_DRIVER, MYSQL_DATABASE_URL, MYSQL_DATABASE_USERNAME, MYSQL_DATABASE_PASSWORD);

        DoctorDao doctorDao = new JDBCDoctorDao(MICROSOFT_DRIVER, MICROSOFT_CONNECTION_URL);
        PatientDao patientDao = new JDBCPatientDao(MICROSOFT_DRIVER, MICROSOFT_CONNECTION_URL);

        Doctor doctorToAdd = new Doctor();
        doctorToAdd.setName("Anna");
        doctorToAdd.setSurname("Nowak");
        doctorToAdd.setSpecialization("Surgeon");

        doctorDao.addDoctor(doctorToAdd);

        Doctor doctorFromDB = doctorDao.getDoctorByNameSurname("Anna", "Nowak");
        System.out.println(doctorFromDB);
        Doctor doctorFromDB2 = doctorDao.getDoctorById(doctorFromDB.getId());
        System.out.println(doctorFromDB2);

        Patient patientToAdd = new Patient();
        patientToAdd.setName("Jan");
        patientToAdd.setSurname("Kowalski");
        patientToAdd.setBirthDate(new Date(new java.util.Date().getTime()));
        doctorDao.addPatient(doctorFromDB.getId(), patientToAdd);

        Patient patientFromDB = patientDao.getPatientById(1);
        System.out.println(patientFromDB);
        Patient patientFromDB2 = patientDao.getPatientByNameSurname("Jan", "Kowalski");
        System.out.println(patientFromDB2);

        Prescription prescription = new Prescription();
        prescription.setMedicine("Paracetamolum");
        prescription.setDate(new Date(new java.util.Date().getTime()));
        prescription.setDosageMg(15.5f);
        prescription.setDosageADay(2);
        patientDao.addPrescription(1, prescription);

        Treatment treatment = new Treatment();
        treatment.setStartDate(new Date(new java.util.Date().getTime()));
        treatment.setEndDate(new Date(new java.util.Date().getTime()));
        treatment.setTitle("X-ray");
        treatment.setDescription("x-ray of left hand");
        patientDao.addTreatment(1, treatment);

        Patient patientFromDB3 = patientDao.getPatientByNameSurname("Jan", "Kowalski");
        System.out.println(patientFromDB3);

        System.out.println("Prescriptions");
        patientFromDB3.getPrescriptions().forEach(System.out::println);
        System.out.println("Treatments");
        patientFromDB3.getTreatments().forEach(System.out::println);
    }

    private static void dropAndCreateDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);
        Connection connection = DriverManager.getConnection(MICROSOFT_CONNECTION_URL);
        Statement statement = connection.createStatement();
        statement.execute("drop table if exists DPRESCRIPTIONS");
        statement.execute("drop table if exists DTREATMENTS");
        statement.execute("drop table if exists DPATIENTS");
        statement.execute("drop table if exists DDOCTORS");
        statement.execute("create table DDOCTORS (\n" +
                "       id integer not null IDENTITY(1,1),\n" +
                "        name varchar(255),\n" +
                "        specialization varchar(255),\n" +
                "        surname varchar(255),\n" +
                "        primary key (id)\n" +
                "    )");
        statement.execute("create table DPATIENTS (\n" +
                "       id integer not null IDENTITY(1,1),\n" +
                "       docId integer ,\n" +
                "        name varchar(255),\n" +
                "        birthDate date,\n" +
                "        surname varchar(255),\n" +
                "        primary key (id),\n" +
                "        foreign key (docId) references DDOCTORS(id)\n" +
                "    )");
        statement.execute("create table DPRESCRIPTIONS (\n" +
                "       id integer not null IDENTITY(1,1),\n" +
                "       patId integer ,\n" +
                "        medicine varchar(255),\n" +
                "        dosageMg real,\n" +
                "        dosageADay integer,\n" +
                "        prescriptionDate date,\n" +
                "        primary key (id),\n" +
                "        foreign key (patId) references DPATIENTS(id)\n" +
                "    )");
        statement.execute("create table DTREATMENTS (\n" +
                "       id integer not null IDENTITY(1,1),\n" +
                "       patId integer ,\n" +
                "        title varchar(255),\n" +
                "        description varchar(255),\n" +
                "        startDate date,\n" +
                "        endDate date,\n" +
                "        primary key (id),\n" +
                "        foreign key (patId) references DPATIENTS(id)\n" +
                "    )");
        statement.close();
    }
}
