package com.example.doctormanagement.rest;

public interface DatabaseData {
    String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    String MYSQL_DATABASE_URL = "jdbc:mysql://localhost:3306/hospital";
    String MYSQL_DATABASE_USERNAME = "root";
    String MYSQL_DATABASE_PASSWORD = "root";

    String MICROSOFT_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String MICROSOFT_DATABASE_URL = "jdbc:sqlserver://miisu.database.windows.net:1433/HIS";
    String MICROSOFT_DATABASE_USERNAME = "ServerAdmin@miisu";
    String MICROSOFT_DATABASE_PASSWORD = "Ristolainen555";
    String MICROSOFT_CONNECTION_URL = "jdbc:sqlserver://miisu.database.windows.net:1433" + ";" +
            "database=HIS" + ";" +
            "user=ServerAdmin@miisu" + ";" +
            "password=Ristolainen555";
}
