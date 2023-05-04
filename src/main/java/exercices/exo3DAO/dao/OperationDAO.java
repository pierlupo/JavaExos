package exercices.exo3DAO.dao;


import jdk.jshell.spi.ExecutionControl;
import exercices.exo3DAO.model.Operation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO extends BaseDAO<Operation> {

    public OperationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Operation element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO customer (amount, account_id, status) values (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, element.getAmount());
        statement.setInt(2, element.getAccountId());
        statement.setInt(3, element.getStatus().ordinal());
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        int rowNb =statement.executeUpdate();

        return rowNb == 1;
    }

    @Override
    public Operation getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Operation DAO");
    }

    @Override
    public List<Operation> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Operation DAO");
    }

    public List<Operation> getAllAccountId(int accountId) throws ExecutionControl.NotImplementedException, SQLException {
        List<Operation> operations = new ArrayList<>();
        request = "SELECT * FROM operation where account_id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, accountId);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Operation o = new Operation(resultSet.getInt("id"), resultSet.getDouble("amount"), accountId);
            operations.add(o);
        }
        return operations;
    }

    @Override
    public boolean update(Operation element) throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("operation DAO");
    }
}
