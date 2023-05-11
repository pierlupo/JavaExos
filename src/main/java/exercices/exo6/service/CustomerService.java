package exercices.exo6.service;

import exercices.exo6.dao.CustomerDAO;
import exercices.exo6.entity.Customer;
import exercices.exo6.entity.Event;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;
    private Connection connection;

    public CustomerService() {
        try {
            connection = new DataBaseManager().getConnection();
            customerDAO = new CustomerDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createCustomer(String firstName, String lastName, String email,List<Event> numOfTicketsBought) {
        Customer customer = new Customer(firstName, lastName, email, numOfTicketsBought);

        try {
            if (customerDAO.save(customer)) {
                return true;
            }
        } catch (ExecutionControl.NotImplementedException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateCustomer(int id, String firstName, String lastName, String email,List<Event> numOfTicketsBought ) {
        try{
            Customer customer = getCustomer(id);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setNumOfTicketsBought(numOfTicketsBought);
            if(customerDAO.update(customer)) {
                return true;}
        }catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Customer getCustomer(int id) {
        Customer customer = null;
        try {
            try {
                customer = customerDAO.getById(id);
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public boolean deleteCar(int id) {
        Customer customer = null;
        try {
            try {
                customer = customerDAO.getById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (customer != null) {
                try {
                    return customerDAO.delete(customer);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public List<Customer> getAll(){
        try {
            return customerDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}


