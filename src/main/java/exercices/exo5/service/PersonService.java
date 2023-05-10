package exercices.exo5.service;

import exercices.exo5.dao.PersonDAO;
import exercices.exo5.entity.Car;
import exercices.exo5.entity.Person;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PersonService {

    private PersonDAO personDAO;
    private Connection connection;

    public PersonService() {
        try {
            connection = new DataBaseManager().getConnection();
            personDAO = new PersonDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createPerson(String lastName, String firstName, int age) {
        Person person = new Person(lastName, firstName, age);

        try {
            if (personDAO.save(person)) {
                return true;
            }
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updatePerson(int id, String lastName, String firstName, int age) {
        try {
            Person person = getPerson(id);
            person.setLastName(lastName);
            person.setFirstName(firstName);
            person.setAge(age);
            if (personDAO.update(person)) {
                return true;
            }
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Person getPerson(int id) {
        Person person = null;
        try {
            try {
                person = personDAO.getById(id);
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public boolean deletePerson(int id) {
        Person person = null;
        try {
            try {
                person = personDAO.getById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (person != null) {
                try {
                    return personDAO.delete(person);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Person> getAll(){
        try {
            return personDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}