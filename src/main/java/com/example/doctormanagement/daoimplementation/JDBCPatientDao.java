package com.example.doctormanagement.daoimplementation;

import com.example.doctormanagement.daointerface.PatientDao;
import com.example.doctormanagement.model.Patient;
import com.example.doctormanagement.model.Prescription;
import com.example.doctormanagement.model.Treatment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public JDBCPatientDao(String driverName, String connectionUrl) throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionUrl);
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
                    " FROM DPATIENTS p" +
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
                toReturn.setTreatments(getTreatmentsByPatientId(id));
                toReturn.setPrescriptions(getPrescriptionsByPatientId(id));
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
                    " FROM DPATIENTS p" +
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
                toReturn.setTreatments(getTreatmentsByPatientId(pid));
                toReturn.setPrescriptions(getPrescriptionsByPatientId(pid));
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
            PreparedStatement statement = connection.prepareStatement("insert into DTREATMENTS " +
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
            PreparedStatement statement = connection.prepareStatement("insert into DPRESCRIPTIONS " +
                    "(patId, medicine, dosageADay, dosageMg, prescriptionDate) " +
                    "values (?, ?, ?, ?, ?)");
            statement.setInt(1, patientId);
            statement.setString(2, prescription.getMedicine());
            statement.setInt(3, prescription.getDosageADay());
            statement.setFloat(4, prescription.getDosageMg());
            statement.setDate(5, prescription.getDate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private List<Treatment> getTreatmentsByPatientId(int id){
        List<Treatment> toReturn = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select" +
                    " p.id," +
                    " p.patId," +
                    " p.description," +
                    " p.title," +
                    " p.startDate, " +
                    " p.endDate " +
                    " FROM DTREATMENTS p" +
                    " WHERE" +
                    " (p.patId = ?)");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Treatment p = new Treatment();
                Integer mhid = resultSet.getInt("id");
                Integer pid = resultSet.getInt("patId");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                p.setId(mhid);
                p.setPatientId(pid);
                p.setTitle(title);
                p.setDescription(description);
                p.setStartDate(startDate);
                p.setEndDate(endDate);
                toReturn.add(p);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return toReturn;
    }

    private List<Prescription> getPrescriptionsByPatientId(int id){
        List<Prescription> toReturn = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select" +
                    " p.id," +
                    " p.patId," +
                    " p.dosageADay," +
                    " p.medicine," +
                    " p.prescriptionDate, " +
                    " p.dosageMg " +
                    " FROM DPRESCRIPTIONS p" +
                    " WHERE" +
                    " (p.patId = ?)");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prescription p = new Prescription();
                Integer mhid = resultSet.getInt("id");
                Integer pid = resultSet.getInt("patId");
                Integer dosageADay = resultSet.getInt("dosageADay");
                String medicine = resultSet.getString("medicine");
                Date prescriptionDate = resultSet.getDate("prescriptionDate");
                Float dosageMg = resultSet.getFloat("dosageMg");
                p.setId(mhid);
                p.setPatientId(pid);
                p.setDosageADay(dosageADay);
                p.setDosageMg(dosageMg);
                p.setDate(prescriptionDate);
                p.setMedicine(medicine);
                toReturn.add(p);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return toReturn;
    }
}
