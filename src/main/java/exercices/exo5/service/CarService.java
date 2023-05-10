package exercices.exo5.service;

import exercices.exo5.dao.CarDAO;
import exercices.exo5.dao.PersonDAO;
import exercices.exo5.entity.Car;
import exercices.exo5.entity.Person;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarService {


    private CarDAO carDAO;
    private Connection connection;

    public CarService() {
        try {
            connection = new DataBaseManager().getConnection();
            carDAO = new CarDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createCar(String name, int year, int horsepower, double price) {
        Car car = new Car(name, year, horsepower, price);

        try {
            if (carDAO.save(car)) {
                return true;
            }
        } catch (ExecutionControl.NotImplementedException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateCar(int id, String name, int year, int power, double price ) {
        try{
        Car car = getCar(id);
        car.setName(name);
        car.setYear(year);
        car.setPrice(power);
        car.setPrice(price);
        if(carDAO.update(car)) {
            return true;}
        }catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
}

    public Car getCar(int id) {
        Car car = null;
        try {
            try {
                car = carDAO.getById(id);
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public boolean deleteCar(int id) {
        Car car = null;
        try {
            try {
                car = carDAO.getById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (car != null) {
                try {
                    return carDAO.delete(car);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public List<Car> getAll(){
        try {
            return carDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
