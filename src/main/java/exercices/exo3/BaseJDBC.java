package exercices.exo3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class BaseJDBC {

    protected static PreparedStatement statement;

    protected static String request;

    protected static Connection connection;

    protected static ResultSet resultSet;
}
