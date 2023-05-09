package exercices.exo4.dao;

import exercices.exo4.model.Product;
import jdk.jshell.spi.ExecutionControl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

        private Connection _connection;
        private PreparedStatement statement;
        private String request;
        private ResultSet resultSet;

        public ProductDAO(Connection connection) {
            this._connection = connection;
        }




    public boolean save(Product product) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO product (name, price, quantity,description) values (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setString(4, product.getDescription());
        int rowNb =statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            product.setIdProduct(resultSet.getInt(1));
        }


        return rowNb == 1;
    }


    public Product getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Product product = null;
        request = "SELECT * FROM product where id_product = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            product = new Product(resultSet.getInt("id_product"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("description")
            );
        }
        return product;
    }


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



    public boolean update(Product product) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE product SET name = ?, price = ?, quantity = ?, description = ? WHERE id_product = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setString(4, product.getDescription());
        statement.setInt(5, product.getIdProduct());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }


    public boolean delete(int id) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM product where id_product = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        int rowNb =statement.executeUpdate();
        return rowNb > 0;
    }
}

