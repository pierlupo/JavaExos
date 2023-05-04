package exercices.exo3;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Account extends BaseJDBC {

        private static long nbComptes = 0;

        private int id;

        private int customerId;

        private Customer customer;

        private List<Operation> operations;

        private double totalAmount;

    public Account(int customerId, double totalAmount) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public Account(int id, int customerId, double totalAmount) {
        this(customerId, totalAmount);
        this.id = id;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    public int getId() {

        return id;
    }

    public int getCustomerId() {

        return customerId;
    }

    public List<Operation> getOperations() {

        return operations;
    }

    public boolean save() throws SQLException {
        request = "INSERT INTO account (total_amount, customer_id) values (?,?)";
        connection = new DataBaseExo3Manager().getConnection();
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, getTotalAmount());
        statement.setInt(2, getCustomerId());

        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            this.id = resultSet.getInt(1);
        }
        int rowNb =statement.executeUpdate();

        return rowNb == 1;
    }


    public boolean update() throws SQLException {
        request = "UPDATE bank_account set total_amount = ? where id = ?";
        connection = new DataBaseExo3Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setDouble(1, getTotalAmount());
        statement.setInt(2, getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }
    public boolean makeDeposit(Operation operation) throws SQLException {
        if(operation.getAmount() > 0 && operation.save()) {
            operations.add(operation);
            totalAmount += operation.getAmount();
            return update();
        }
        return false;
    }
    public boolean makeWithDrawl(Operation operation) throws SQLException {
        if(operation.getAmount() < 0 && getTotalAmount() >= operation.getAmount() && operation.save()) {
            operations.add(operation);
            totalAmount += operation.getAmount();
            return update();
        }
        return false;
    }
    public static Account getById(int id) throws SQLException {
        Account account = null;
        request = "SELECT * FROM bank_account where id = ?";
        connection = new DataBaseExo3Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            account = new Account(resultSet.getInt("id"),
                    resultSet.getInt("customer_id"),
                    resultSet.getDouble("total_amount")
            );
        }

        //assert account != null;
        account.customer = Customer.getById(account.getId());
        account.operations = Operation.getAllByAccountId(account.getId());
        return account;
    }

}

