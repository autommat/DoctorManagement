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
        throw new NotImplementedException();
    }

    public boolean addPrescription(int patientId, Prescription prescription) {
        throw new NotImplementedException();
    }
}
