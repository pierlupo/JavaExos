package exercices.exo6.service;

import exercices.exo6.dao.CustomerDAO;
import exercices.exo6.dao.EventDAO;
import exercices.exo6.entity.Customer;
import exercices.exo6.entity.Event;
import exercices.exo6.entity.Place;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EventService {

    private EventDAO eventDAO;
    private Connection connection;

    public EventService() {
        try {
            connection = new DataBaseManager().getConnection();
            eventDAO = new EventDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createEvent(String name, String date, String hour, Place place, float price) {
        Event event = new Event(name, date, hour, place, price);

        try {
            if (eventDAO.save(event)) {
                return true;
            }
        } catch (ExecutionControl.NotImplementedException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateEvent(int id, String name, String date, String hour, Place place, float price ) {
        try{
            Event event = getEvent(id);
            event.setName(name);
            event.setDate(date);
            event.setHour(hour);
            event.setPlace(place);
            event.setPrice(price);
            if(eventDAO.update(event)) {
                return true;}
        }catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Event getEvent(int id) {
        Event event = null;
        try {
            try {
                event = eventDAO.getById(id);
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return event;
    }

    public boolean deleteEvent(int id) {
        Event event = null;
        try {
            try {
                event = eventDAO.getById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (event != null) {
                try {
                    return eventDAO.delete(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public List<Event> getAll(){
        try {
            return eventDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
