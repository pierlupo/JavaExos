package org.example.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    private static final String URI = "jdbc:mysql://localhost:3306/exercice6_jdbc";

    private static final String USER = "root";

    private static final String PASSWORD = "Guerrier@777";

    private static DataBaseManager instance = null;




    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URI, USER, PASSWORD);
    }
}

