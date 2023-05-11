package exercices.exo6.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Event> numOfTicketsBought = new ArrayList<>();

    public Customer(String firstName, String lastName, String email, List<Event> numOfTicketsBought) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numOfTicketsBought = numOfTicketsBought;
    }

    public Customer(int id, String firstName, String lastName, String email, List<Event> numOfTicketsBought) {
        this(firstName,lastName,email,numOfTicketsBought);
        this.id = id;
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Event> getNumOfTicketsBought() {
        return numOfTicketsBought;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumOfTicketsBought(List<Event> numOfTicketsBought) {
        this.numOfTicketsBought = numOfTicketsBought;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", numOfTicketsBought=" + numOfTicketsBought +
                '}';
    }


}

