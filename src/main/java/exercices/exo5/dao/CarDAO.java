package exercices.exo5.dao;

import exercices.exo5.entities.Car;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends BaseDAO<Car>{
    public CarDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Car car) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO Car (name, year, horsePower, price) values (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, car.getName());
        statement.setInt(2, car.getYear());
        statement.setInt(3, car.getHorsePower());
        statement.setDouble(3, car.getPrice());

        int rowNb = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            car.setId(resultSet.getInt(1));
        }
        return rowNb == 1;
    }

    @Override
    public Car getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Car car = null;
        request = "SELECT * FROM car WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            car = new Car(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("year"),
                    resultSet.getInt("horsepower"),
                    resultSet.getDouble("price")
            );
        }
        return car;
    }

    @Override
    public List<Car> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Car> cars = new ArrayList<>();
        request = "SELECT * FROM car";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Car c = new Car(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getInt("year"), resultSet.getInt("horsepower"), resultSet.getDouble("price"));
            cars.add(c);
        }
        return cars;
    }

    @Override
    public boolean update(Car element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE car SET model = ?, year = ?,power = ?,price = ? WHERE id =?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,element.getName());
        statement.setInt(2,element.getYear());
        statement.setInt(3,element.getHorsePower());
        statement.setDouble(4,element.getPrice());
        statement.setInt(5,element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Car element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM car WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
