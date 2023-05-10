package exercices.exo5.service;

import exercices.exo5.dao.CarDAO;
import exercices.exo5.dao.PersonDAO;
import exercices.exo5.dao.SaleDAO;
import exercices.exo5.entity.Car;
import exercices.exo5.entity.Person;
import exercices.exo5.entity.Sale;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SaleService {


    private SaleDAO saleDAO;
    private CarDAO carDAO;
    private PersonDAO personDAO;
    private Connection connection;

    public SaleService() {
        try {
            connection = new DataBaseManager().getConnection();
            connection.setAutoCommit(false);
            saleDAO = new SaleDAO(connection);
            personDAO = new PersonDAO(connection);
            carDAO = new CarDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createSale(int carId, int personId) {
        try {
            Person person = personDAO.getById(personId);
            Car car = carDAO.getById(carId);
            if (person != null && car != null) {
                Sale sale = new Sale(new Date(), car, person);

                if(saleDAO.save(sale)){
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return false;
        }

    public Sale getSale(int id) {
        try {
            return saleDAO.getById(id);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sale> getSales() {
        try {
            return saleDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}