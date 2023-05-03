package org.example.exercices.exo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseExo1Manager {

    private static final String URI = "jdbc:mysql://localhost:3306/exercice1_jdbc";

    private static final String USER = "root";

    private static final String PASSWORD = "*********";

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URI, USER, PASSWORD);
    }
}
