package com.example.doctormanagement.daoimplementation;

import com.example.doctormanagement.daointerface.DoctorDao;
import com.example.doctormanagement.model.Doctor;
import com.example.doctormanagement.model.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JDBCDoctorDao implements DoctorDao {

    private Connection connection;

    public JDBCDoctorDao(String driverName,
            String databaseURL,
            String databaseUser,
            String databasePassword) throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
        } catch (ClassNotFoundException e) {
            System.out.println("problems with JDBC connector");
            throw e;
        } catch (SQLException e) {
            System.out.println("no connection to database");
            throw e;
        }
    }

    public boolean addPatient(int doctorId, Patient patient) {
        throw new NotImplementedException();
    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = new Doctor();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DOCTORS WHERE id=(?)");
            ps.setInt(1, id);
            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setSurname(rs.getString("surname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctor;
    }

    public Doctor getDoctorByNameSurname(String name, String surname) {
        Doctor doctor = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DOCTORS WHERE (name=(?) AND surname=(?))");
            ps.setString(1, name);
            ps.setString(2, surname);
            if (ps.execute()) {
                doctor = new Doctor();
                ResultSet rs = ps.getResultSet();
                doctor.setName(name);
                doctor.setSurname(surname);
                doctor.setSpecialization(rs.getString("specialization"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctor;
    }
}
