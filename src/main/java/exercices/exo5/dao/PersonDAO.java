package exercices.exo5.dao;

import exercices.exo5.entity.Person;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO extends BaseDAO<Person>{
    public PersonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Person person) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO person (last_name, first_name, age) values (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, person.getLastName());
        statement.setString(2, person.getFirstName());
        statement.setInt(3, person.getAge());

        int rowNb =statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            person.setId(resultSet.getInt(1));
        }


        return rowNb == 1;
    }

    @Override
    public Person getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Person person = null;
        request = "SELECT * FROM person WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            person = new Person(resultSet.getInt("id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getInt("age")
            );
        }
        return person;
    }

    @Override
    public List<Person> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Person> persons = new ArrayList<>();
        request = "SELECT * FROM person";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Person p = new Person(resultSet.getInt("id"),resultSet.getString("last_name"),resultSet.getString("first_name"), resultSet.getInt("age"));
            persons.add(p);
        }
        return persons;
    }

    @Override
    public boolean update(Person element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE personne set last_name = ?, first_name = ?, age = ? where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getFirstName());
        statement.setString(2,element.getLastName());
        statement.setInt(3,element.getAge());
        statement.setInt(4,element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Person element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM person WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
