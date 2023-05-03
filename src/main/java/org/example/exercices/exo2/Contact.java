package org.example.exercices.exo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contact {

    private int id;

    private String lastName;

    private String firstName;

    private String tel;

    private List<Email> emails;

    private static String request;

    private static PreparedStatement statement;

    private static Connection connection;

    private static ResultSet resultSet;

    public Contact( String lastName, String firstName, String tel) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.tel = tel;
    }

    public Contact(int id,String lastName, String firstName, String tel) {
        this( lastName, firstName, tel);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public static String getRequest() {
        return request;
    }

    public static void setRequest(String request) {
        Contact.request = request;
    }

    public static PreparedStatement getStatement() {
        return statement;
    }

    public static void setStatement(PreparedStatement statement) {
        Contact.statement = statement;
    }

    public boolean save() throws SQLException {
        request = "INSERT INTO contact (nom, prenom, tel) values (?,?,?)";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, getFirstName());
        statement.setString(2, getLastName());
        statement.setString(3, getTel());
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            this.id = resultSet.getInt(1);
        }
        int rowNb =statement.executeUpdate();

        return rowNb > 0;
    }

    public boolean update() throws SQLException {
        request = "UPDATE contact SET nom = ?, prenom = ?, tel = ? WHERE ID = ?";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setString(1, getFirstName());
        statement.setString(2, getLastName());
        statement.setString(3, getTel());
        statement.setInt(4, getId());
        int rowNb =statement.executeUpdate();

        return rowNb > 0;
    }

    public boolean delete() throws SQLException {
        request = "DELETE FROM contact WHERE contact_id = ?";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1, getId());
        int rowNb =statement.executeUpdate();
        return rowNb > 0;
    }


    public static Contact getById(int id) throws SQLException {
        Contact contact = null;
        request = "SELECT * FROM contact WHERE contact_id = ?";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            contact = new Contact(resultSet.getInt("contact_id"),resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("tel"));
        }
        return contact;
    }

//    public static Contact getByLastName(String lastName) throws SQLException {
//        Contact contact = null;
//        request = "SELECT * FROM contact WHERE nom = ?";
//        connection = new DataBaseExo2Manager().getConnection();
//        statement = connection.prepareStatement(request);
//        statement.setString(1, lastName);
//        ResultSet resultSet = statement.executeQuery();
//        if (resultSet.next()) {
//            contact = new Contact(resultSet.getInt("contact_id"),resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("tel"));
//        }
//        return contact;
//    }
//
//
//    public static Contact getByFirstName(String firstName) throws SQLException {
//        Contact contact = null;
//        request = "SELECT * FROM contact WHERE prenom = ?";
//        connection = new DataBaseExo2Manager().getConnection();
//        statement = connection.prepareStatement(request);
//        statement.setString(1, firstName);
//        ResultSet resultSet = statement.executeQuery();
//        if (resultSet.next()) {
//            contact = new Contact(resultSet.getInt("contact_id"),resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("tel"));
//        }
//        return contact;
//    }
//
//    public static Contact getByTel(String tel) throws SQLException {
//        Contact contact = null;
//        request = "SELECT * FROM contact WHERE tel = ?";
//        connection = new DataBaseExo2Manager().getConnection();
//        statement = connection.prepareStatement(request);
//        statement.setString(1, tel);
//        ResultSet resultSet = statement.executeQuery();
//        if (resultSet.next()) {
//            contact = new Contact(resultSet.getInt("contact_id"),resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("tel"));
//        }
//        return contact;
//    }

    public static List<Contact> search(String word) throws SQLException {
        List<Contact> results = new ArrayList<>();
        request = "SELECT * FROM contact WHERE nom LIKE ? OR prenom LIKE ? OR tel LIKE ?";
        connection = new DataBaseExo2Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setString(1, word + "%");
        statement.setString(2, word + "%");
        statement.setString(3, word + "%");
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Contact contact = new Contact(resultSet.getInt("contact_id"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("tel"));
            results.add(contact);
        }
        return results;
    }
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
