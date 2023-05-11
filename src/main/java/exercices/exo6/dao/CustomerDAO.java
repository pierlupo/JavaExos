package exercices.exo6.dao;

import exercices.exo6.entity.Customer;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer>{
    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Customer customer) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO customer (first_name, last_name, email, number_of_tickets) values (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getEmail());
        statement.setInt(4, customer.getNumOfTicketsBought().size());

        int rowNb = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            customer.setId(resultSet.getInt(1));
        }
        return rowNb == 1;
    }

    @Override
    public Customer getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Customer customer = null;
        request = "SELECT * FROM customer WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customer = new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email")

            );
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Customer> customers = new ArrayList<>();
        request = "SELECT * FROM customer";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customer c = new Customer(resultSet.getInt("id"),resultSet.getString("first_name"), resultSet.getString("last_name"),resultSet.getString("email"));
            customers.add(c);
        }
        return customers;
    }

    @Override
    public boolean update(Customer customer) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE customer SET first_name = ?, last_name = ?,email = ?,number_of_tickets = ? WHERE id =?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,customer.getFirstName());
        statement.setString(2,customer.getLastName());
        statement.setString(3,customer.getEmail());
        statement.setInt(4,customer.getNumOfTicketsBought().size());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Customer customer) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM customer WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, customer.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
