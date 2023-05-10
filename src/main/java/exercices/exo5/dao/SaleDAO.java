package exercices.exo5.dao;

import exercices.exo5.entity.Car;
import exercices.exo5.entity.Person;
import exercices.exo5.entity.Sale;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO extends BaseDAO<Sale> {
    public SaleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Sale sale) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO Sale (car_id, person_id, sale_date) values (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, sale.getCar().getId());
        statement.setInt(2, sale.getPerson().getId());
        statement.setDate(3, new Date(sale.getSaleDate().getTime()));
        int rowNb = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            sale.setId(resultSet.getInt(1));
        }
        return rowNb == 1;
    }

    @Override
    public Sale getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Sale sale = null;
        request = "SELECT s.person_id , s.car_id, s.sale_date, p.last_name, p.first_name,  p.age, c.name, c.year, c.horsepower, c.price FROM sale as s inner join car as c on s.car_id = c.id inner join person as p on s.person_id = p.id where s.id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            sale = new Sale(id, resultSet.getDate("sale_date"));
            sale.setCar(new Car(resultSet.getInt("car_id"),
                    resultSet.getString("model"),
                    resultSet.getInt("power"),
                    resultSet.getInt("price"),
                    resultSet.getInt("year")));
            sale.setPerson(new Person(resultSet.getInt("person_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")));
        }
        return sale;
    }

    @Override
    public List<Sale> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Sale> result = new ArrayList<>();
        request = "SELECT s.id, s.person_id , s.car_id, s.sale_date, p.last_name, p.first_name, p.age, c.name, c.year, c.horsepower, c.price FROM sale as s inner join car as c on s.car_id = c.id inner join person as p on s.person_id = p.id";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            Sale sale = new Sale(resultSet.getInt("id"), resultSet.getDate("sale_date"));
            sale.setCar(new Car(resultSet.getInt("car_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("horsepower"),
                    resultSet.getInt("price"),
                    resultSet.getInt("year")));
            sale.setPerson(new Person(resultSet.getInt("person_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")));
            result.add(sale);
        }
        return result;
    }

    @Override
    public boolean update(Sale element) throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Sale DAO");
    }

    @Override
    public boolean delete(Sale element) throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Sale DAO");
    }
}
