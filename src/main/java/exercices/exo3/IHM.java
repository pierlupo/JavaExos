package exercices.exo3;

import java.sql.SQLException;
import java.util.Scanner;

public class IHM {

    private Scanner scanner;
    String choix;

    public IHM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        String choix;
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    createAccountAction();

                    break;
                case "2":
                    depositAction();

                    break;
                case "3":
                    withDrawlAction();

                    break;
                case "4":
                    getAccountAction();

                    break;
            }

        } while (!choix.equals("0"));

    }

    private void menu() {

        System.out.println("1 - Créer un compte");
        System.out.println("2 - Effectuer un dépôt");
        System.out.println("3 - Effectuer un retrait");
        System.out.println("4 - Afficher le compte");
        System.out.println("0 - Quitter");
    }


    private Customer createCustomerAction() {
        Customer customer = null;
        System.out.print("Merci de saisir le nom : ");
        String lastName = scanner.nextLine();
        System.out.print("Merci de saisir le prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Merci de saisir le téléphone : ");
        String phone = scanner.nextLine();
        customer = new Customer(firstName, lastName, phone);
        try {
            if (customer.save()) {
                System.out.println("Client ajouté " + customer.getId());
            } else {
                customer = null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            customer = null;
        }
        return customer;
    }

    private void createAccountAction() {
        Customer customer = createCustomerAction();
        if (customer != null) {
            Account account = new Account(customer.getId(), 0);
            try {
                if (account.save()) {
                    System.out.println("Compté créé avec l'id " + account.getId());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void depositAction() {
        Account account = getAccountAction();
        System.out.print("Merci de saisir le montant du dépôt : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        Operation operation = new Operation(montant, account.getId());
        try {
            if (account.makeDeposit(operation)) {
                System.out.println("Dépôt Ok");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void withDrawlAction() {
        Account account = getAccountAction();
        System.out.print("Merci de saisir le montant du dépôt : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        Operation operation = new Operation(montant * -1, account.getId());
        try {
            if (account.makeWithDrawl(operation)) {
                System.out.println("Retrait Ok");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Account getAccountAction() {
        Account account = null;
        System.out.print("Merci de saisir l'id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            account = Account.getById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (account != null) {
            System.out.println(account);
        }
        return account;
    }
}