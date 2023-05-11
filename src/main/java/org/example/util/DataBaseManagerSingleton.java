package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManagerSingleton {

    private DataBaseManagerSingleton() {
    }

    private static final String URI = "jdbc:mysql://localhost:3306/exercice6_jdbc";

    private static final String USER = "root";

    private static final String PASSWORD = "Guerrier@777";

    private static DataBaseManagerSingleton instance = null;


    public static DataBaseManagerSingleton getInstance(){
        if(instance == null)
            instance = new DataBaseManagerSingleton();
        return instance;
    }

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URI, USER, PASSWORD);
    }
}

