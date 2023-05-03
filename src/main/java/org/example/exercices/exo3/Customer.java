package org.example.exercices.exo3;

import org.example.exercices.exo2.Contact;
import org.example.exercices.exo2.DataBaseExo2Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer extends BaseJDBC{

    private int id;

    private String firstName;
    private String lastName;
    private String phone;

    public Customer(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Customer(int id, String firstName, String lastName, String phone) {
        this(firstName,lastName,phone) ;
        this.id = id;
    }


    public boolean save() throws SQLException {
        request = "INSERT INTO customer (first_name, last_name, phone) values (?,?,?)";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, getFirstName());
        statement.setString(2, getLastName());
        statement.setString(3, getPhone());
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            this.id = resultSet.getInt(1);
        }
        int rowNb =statement.executeUpdate();

        return rowNb == 1;
    }

    public static Customer getById(int id) throws SQLException {
        Customer customer = null;
        request = "SELECT * FROM customer WHERE id = ?";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customer = new Customer(resultSet.getInt("id"),resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("phone"));
        }
        return customer;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
