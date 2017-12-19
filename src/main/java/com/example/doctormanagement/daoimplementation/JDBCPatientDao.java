package com.example.doctormanagement.daoimplementation;

import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Prescription;
import com.example.doctormanagement.model.Treatment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;

public class JDBCPatientDao implements PatientDao {

    private Connection connection;

    public JDBCPatientDao(String driverName,
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

    public Patient getPatientById(int id) {
        Patient toReturn = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select" +
                    " p.id," +
                    " p.surname," +
                    " p.name," +
                    " p.birthDate " +
                    " FROM PATIENTS p" +
                    " WHERE" +
                    " (p.id = ?)");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toReturn = new Patient();
                Integer pid = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthDate = resultSet.getDate("birthDate");
                toReturn.setId(pid);
                toReturn.setName(name);
                toReturn.setSurname(surname);
                toReturn.setBirthDate(birthDate);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return toReturn;
    }

    public Patient getPatientByNameSurname(String name, String surname) {
        Patient toReturn = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select" +
                    " p.id," +
                    " p.surname," +
                    " p.name," +
                    " p.birthDate " +
                    " FROM PATIENTS p" +
                    " WHERE" +
                    " (p.name = ? AND p.surname=?)");
            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toReturn = new Patient();
                Integer pid = resultSet.getInt("id");
                String pname = resultSet.getString("name");
                String psurname = resultSet.getString("surname");
                Date birthDate = resultSet.getDate("birthDate");
                toReturn.setId(pid);
                toReturn.setName(name);
                toReturn.setSurname(surname);
                toReturn.setBirthDate(birthDate);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return toReturn;
    }

    public boolean addTreatment(int patientId, Treatment treatment) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into TREATMENTS " +
                    "(patId, title, description, startDate, endDate) " +
                    "values (?, ?, ?, ?, ?)");
            statement.setInt(1, patientId);
            statement.setString(2, treatment.getTitle());
            statement.setString(3, treatment.getDescription());
            statement.setDate(4, treatment.getStartDate());
            statement.setDate(5, treatment.getEndDate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addPrescription(int patientId, Prescription prescription) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into PRESCRIPTIONS " +
                    "(patId, medicine, dosageADay, dosageMg) " +
                    "values (?, ?, ?, ?)");
            statement.setInt(1, patientId);
            statement.setString(2, prescription.getMedicine());
            statement.setInt(3, prescription.getDosageADay());
            statement.setFloat(4, prescription.getDosageMg());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
