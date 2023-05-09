package exercices.exo5;

import exercices.exo5.dao.CarDAO;
import exercices.exo5.dao.PersonDAO;
import exercices.exo5.entities.Car;
import exercices.exo5.entities.Person;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class IHM {

    Scanner scanner;
    String choix;

    private CarDAO carDAO;
    private PersonDAO personDAO;
    private Connection connection;

    public IHM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    AddCarAction();
                    break;
                case "2":
                    getCarByIDAction();
                    break;
                case "3":
                    getAllCarsAction();
                    break;
                case "4":
                    ChangeCarAction();
                    break;
                case "5":
                    DeleteCarAction();
                    break;
                case "6":
                    AddPersonAction();
                    break;
                case "7":
                    getPersonByIdAction();
                    break;
                case "8":
                    getAllPersonsAction();
                    break;
                case "9":
                    ChangePersonAction();
                    break;
                case "10":
                    DeletePersonAction();
                break;
                case "11":
                    CarSaleAction();
                    break;
                case "12":
                    AllSalesAction();
                    break;
                case "12":
                    AllSalesAction();
            }
        } while (!choix.equals("0"));
    }

    private void menu() {
        System.out.println("1 - Add a car ");
        System.out.println("2 - get a car ");
        System.out.println("3 - get all cars ");
        System.out.println("4 - change car ");
        System.out.println("5 - Delete car ");
        System.out.println("6 - Add a person ");
        System.out.println("7 - get a person ");
        System.out.println("8 - get all persons ");
        System.out.println("9 - change person ");
        System.out.println("10 - Delete person ");
        System.out.println("11 - sell a car ");
        System.out.println("12 - all sales ");
        System.out.println("0 - Quit ");
    }

    private Car AddCarAction() {
        Car car = null;
        System.out.print("Name of the car : ");
        String name = scanner.nextLine();
        System.out.print("Year of the car : ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Horsepower : ");
        int horsepower = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Price : ");
        double price = scanner.nextDouble();
        car = new Car(name, year, horsepower, price);
        try {
            connection = new DataBaseManager().getConnection();
            connection.setAutoCommit(false);
            carDAO = new CarDAO(connection);
            if (carDAO.save(car)) {
                System.out.println("Car added " + car.getId());
            } else {
                car = null;
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            car = null;
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

        return car;
    }

    private Person AddPersonAction() {
        Person person = null;
        System.out.print("Enter your lastname : ");
        String lastName = scanner.nextLine();
        System.out.print("Enter your firstname : ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        person = new Person(lastName, firstName, age);
        try {
            connection = new DataBaseManager().getConnection();
            connection.setAutoCommit(false);
           personDAO = new PersonDAO(connection);
            if (personDAO.save(person)) {
                System.out.println("Person added " + person.getId());
            } else {
                person = null;
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            person = null;
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

        return person;
    }
}
