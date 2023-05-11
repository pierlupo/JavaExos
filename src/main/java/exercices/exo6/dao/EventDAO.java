package exercices.exo6.dao;

import exercices.exo6.entity.Customer;
import exercices.exo6.entity.Event;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDAO extends BaseDAO<Event>{
    public EventDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Event event) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO event (name, date, hour, place, price) values (?,?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, event.getName());
        statement.setString(2, event.getDate());
        statement.setString(3, event.getHour());
        //statement.setInt(4, event.getPlace());
        statement.setFloat(5, event.getPrice());

        int rowNb = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            event.setId(resultSet.getInt(1));
        }
        return rowNb == 1;
    }

    @Override
    public Event getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        return null;
    }

    @Override
    public List<Event> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Event> events = new ArrayList<>();
        request = "SELECT * FROM event";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
//        while (resultSet.next()) {
//            Event e = new Event(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("date"),resultSet.getString("hour"),resultSet.getInt("place"),resultSet.getFloat("price"));
//            events.add(e);
//        }
        return events;
    }

    @Override
    public boolean update(Event event) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE customer SET name = ?, date = ?,hour = ?,place = ?,price = ?  WHERE id =?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,event.getName());
        statement.setString(2,event.getDate());
        statement.setString(3,event.getHour());
//        statement.setInt(4,event.getPlace());
        statement.setFloat(5,event.getPrice());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Event event) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM customer WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, event.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
