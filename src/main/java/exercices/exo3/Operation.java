package exercices.exo3;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Operation extends BaseJDBC{

    private int id;
    private double amount;
    private OperationStatus status;
    private int accountId;

    public int getAccountId() {
        return accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public Operation(double amount, int accountId) {
        this.amount = amount;
        this.accountId = accountId;
        this.status = (this.amount >= 0) ? OperationStatus.DEPOSIT : OperationStatus.WITHDRAWAL;
    }

    public Operation(int id, double amount, int accountId) {
        this(amount, accountId);
        this.id = id;
    }

    public boolean save() throws SQLException {
        request = "INSERT INTO customer (amount, account_id, status) values (?,?,?)";
        connection = new DataBaseExo3Manager().getConnection();
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, getAmount());
        statement.setInt(2, getAccountId());
        statement.setInt(3, getStatus().ordinal());
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            this.id = resultSet.getInt(1);
        }
        int rowNb =statement.executeUpdate();

        return rowNb == 1;
    }

    public static List<Operation> getAllByAccountId(int accountId) throws SQLException {
        List<Operation> operations = new ArrayList<>();
        request = "SELECT * FROM operation WHERE account_id = ?";
        connection = new DataBaseExo3Manager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1, accountId);
        resultSet = statement.executeQuery();

     while (resultSet.next()) {
        Operation o = new Operation(resultSet.getInt("id"), resultSet.getDouble("amount"), accountId);
        operations.add(o);
    }
    return operations;
    }
    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", amount=" + amount +
                ", status=" + status +
                ", accountId=" + accountId +
                '}';
    }
}

enum OperationStatus {
    DEPOSIT,
    WITHDRAWAL
}

