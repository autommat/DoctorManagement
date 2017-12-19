package com.example.doctormanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    }

    private static void dropAndCreateDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);
        Connection connection = DriverManager.getConnection(MYSQL_DATABASE_URL, MYSQL_DATABASE_USERNAME, MYSQL_DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("drop table if exists DOCTORS");
        statement.execute("create table DOCTORS (\n" +
                "       id integer not null auto_increment,\n" +
                "        name varchar(255),\n" +
                "        specialization varchar(255),\n" +
                "        surname varchar(255),\n" +
                "        primary key (id)\n" +
                "    )");
        statement.execute("drop table if exists PATIENTS");
        statement.execute("create table PATIENTS (\n" +
                "       id integer not null auto_increment,\n" +
                "       docId integer ,\n" +
                "        name varchar(255),\n" +
                "        specialization varchar(255),\n" +
                "        surname varchar(255),\n" +
                "        primary key (id),\n" +
                "        foreign key (docId) references DOCTORS(id)\n" +
                "    )");

        statement.close();
    }
}
