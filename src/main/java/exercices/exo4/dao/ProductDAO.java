package exercices.exo4.dao;

import exercices.exo4.model.Product;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends BaseDAO<Product> {

    public ProductDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Product element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO product (name, price, quantity,description) values (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setFloat(2, element.getPrice());
        statement.setInt(3, element.getQuantity());
        statement.setString(4, element.getDescription());
        int rowNb =statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            element.setIdProduct(resultSet.getInt(1));
        }


        return rowNb == 1;
    }

    @Override
    public Product getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Product product = null;
        request = "SELECT * FROM product where id_product = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            product = new Product(resultSet.getInt("id_product"),
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("description")
            );
        }
        return product;
    }
    @Override

    public List<Product> getAll() throws ExecutionControl.NotImplementedException, SQLException {
            List<Product> result = new ArrayList<>();
            request = "SELECT * FROM product";
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product p = new Product(resultSet.getInt("id_product"),resultSet.getString("name"), resultSet.getFloat("price"), resultSet.getInt("quantity"), resultSet.getString("description"));
                result.add(p);
            }
            return result;
        }


    @Override
    public boolean update(Product element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE product set name, price, quantity, description = ? where id_product = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setFloat(2, element.getPrice());
        statement.setInt(3, element.getQuantity());
        statement.setString(4, element.getDescription());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }
    @Override
    public boolean delete(int id) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM product where id_product = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        int rowNb =statement.executeUpdate();
        return rowNb > 0;
    }
}

