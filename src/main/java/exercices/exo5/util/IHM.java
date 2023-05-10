package exercices.exo5.util;

import exercices.exo5.dao.CarDAO;
import exercices.exo5.dao.PersonDAO;
import exercices.exo5.dao.SaleDAO;
import exercices.exo5.entity.Car;
import exercices.exo5.entity.Person;
import exercices.exo5.service.CarService;
import exercices.exo5.service.PersonService;
import exercices.exo5.service.SaleService;
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
    private SaleDAO saleDAO;
    private Connection connection;
    private CarService carService;
    private PersonService personService;
    private SaleService saleService;


    public IHM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    createCar();
                    break;
//                case "2":
//                    getCarByIDAction();
//                    break;
                case "3":
                    listCar();
                    break;
                case "4":
                    updateCar();
                    break;
                case "5":
                    deleteCar();
                    break;
                case "6":
                    createPerson();
                    break;
//                case "7":
//                    getPersonByIdAction();
//                    break;
                case "8":
                    listPerson();
                    break;
                case "9":
                    updatePerson();
                    break;
                case "10":
                    deletePerson();
                break;
                case "11":
                    carSale();
                    break;
                case "12":
                    allSales();
                    break;
            }
        } while (!choix.equals("0"));
    }

    private void menu() {
        System.out.println("1 - Add a car ");
        System.out.println("2 - get a car ");
        System.out.println("3 - get all cars ");
        System.out.println("4 - update car ");
        System.out.println("5 - Delete car ");
        System.out.println("6 - Add a person ");
        System.out.println("7 - get a person ");
        System.out.println("8 - get all persons ");
        System.out.println("9 - update person ");
        System.out.println("10 - Delete person ");
        System.out.println("11 - sell a car ");
        System.out.println("12 - all sales ");
        System.out.println("0 - Quit ");
    }

    private void createCar() {
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
        scanner.nextLine();
        carService = new CarService();
        if (carService.createCar(name, year, horsepower, price)) {
            System.out.println("Car added ");
        } else {
            System.out.println("Error while trying to add a new car ");
        }
    }

        private void createPerson() {
            System.out.print("Enter your lastname : ");
            String lastName = scanner.nextLine();
            System.out.print("Enter your firstname : ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your age : ");
            int age = scanner.nextInt();
            scanner.nextLine();
            personService = new PersonService();
            if (personService.createPerson(lastName, firstName, age)) {
                System.out.println("Person added ");
            } else {
                System.out.println("Error while trying to add a new person ");
            }
        }

        private void updateCar() {
            System.out.print("Merci de saisir l'id : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Merci de saisir le nom : ");
            String name = scanner.nextLine();
            System.out.print("Merci de saisir l'année : ");
            int year = scanner.nextInt();
            System.out.print("Merci de saisir le prix : ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Merci de saisir la puissance : ");
            int power = scanner.nextInt();
            scanner.nextLine();
            carService = new CarService();
            if(carService.updateCar(id, name, year, power, price)) {
                System.out.println("voiture modifiée");
            }
            else {
                System.out.println("Erreur modification");
            }
        }

        private void deleteCar() {
            System.out.print("Merci de saisir l'id de la voiture : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            carService = new CarService();
            if(carService.deleteCar(id)) {
                System.out.println("voiture supprimée");
            }
            else {
                System.out.println("Erreur lors de la suppression");
            }
        }

        private void listCar() {
            carService = new CarService();
            carService.getAll().forEach(c -> {
                System.out.println(c);
            });
        }

    private void updatePerson() {
        System.out.print("Merci de saisir l'id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Merci de saisir le nom : ");
        String lastName = scanner.nextLine();
        System.out.print("Merci de saisir le prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Merci de saisir l'âge' : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        personService = new PersonService();
        if(personService.updatePerson(id, lastName, firstName, age)) {
            System.out.println("voiture modifiée");
        }
        else {
            System.out.println("Erreur modification");
        }
    }

        private void deletePerson() {
            System.out.print("Merci de saisir l'id de la personne : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            personService = new PersonService();
            if(personService.deletePerson(id)) {
                System.out.println("personne supprimée");
            }
            else {
                System.out.println("Erreur lors de la suppression");
            }
        }

        private void listPerson() {
            personService = new PersonService();
            personService.getAll().forEach(p -> {
                System.out.println(p);
            });
        }

        private void carSale(){
            System.out.print("Merci de saisir l'id de la personne : ");
            int personId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Merci de saisir l'id de la voiture: ");
            int carId = scanner.nextInt();
            scanner.nextLine();
            saleService = new SaleService();
            if(saleService.createSale(personId,carId)){
                System.out.println("vente effectuée");
            }
            else {
                System.out.println("Erreur lors de la vente");
            }
        }
        private void allSales(){
            saleService = new SaleService();
            saleService.getSales().forEach(s -> {
                System.out.println(s);
            });
        }
    }