package exercices.exo3avectransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseExo3BisManager {
    private static final String URI = "jdbc:mysql://localhost:3306/exercice3_jdbc";

    private static final String USER = "root";

    private static final String PASSWORD = "************";

    public Connection getConnection() throws SQLException

    {
        return DriverManager.getConnection(URI, USER, PASSWORD);
    }
}
