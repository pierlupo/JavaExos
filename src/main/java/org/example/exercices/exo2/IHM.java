package org.example.exercices.exo2;

import java.sql.SQLException;
import java.util.Scanner;

public class IHM {

    private Scanner scanner;
    String choix;
    public  IHM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        String choix;
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    addContactAction();

                    break;
                case "2":
                    editContactAction();

                    break;
                case "3":
                    deleteContactByIdAction();

                    break;
                case "4":
                    searchContactsAction();

                    break;

//                case "0":
//                  //  quitAction();
//
//                    break;

//                default:
//
//                System.out.println("Je n'ai pas compris votre demande veuillez réessayer svp");
//                menu();
            }

        }while (!choix.equals("0"));

    }
//    public void MenugetContactAction () {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("==== Recherche de Contacts ====");
//        System.out.println();
//
//        String[] propositions = {"1- Touver un contact par son nom", "2- Touver un contact par son prénom", "3-Touver un contact par son tel", "0- Retour", "Entrez votre choix : "};
//
//        for (String prop : propositions) {
//            System.out.println(prop);
//        }
//
//        String answer = sc.next();
//
//
//        switch (answer) {
//            case "1":
//                getByFirstName();
//                MenugetContactAction();
//
//            case "2":
//                getByLastname();
//                menu();
//
//            case "3":
//                getByTel();
//                menu();
//            case "4":
//
//                MenugetContactAction();
//                menu();
//
//            case "0":
//                break;
//
//            default:
//                System.out.println("Je n'ai pas compris votre demande ");
//                MenugetContactAction();
//        }
//        menu();
//    }

    private void menu() {
        System.out.println("1 - Ajouter un contact ");
        System.out.println("2 - Modifier un contact");
        System.out.println("3 - Supprimer un contact");
        System.out.println("4 - Recherche de contact");
        System.out.println("0 - Quitter");

    }
    private void addContactAction()  {
        System.out.println("**** Ajouter un contact ****");
        System.out.print("Merci de saisir le nom : ");
        String lastName = scanner.nextLine();
        System.out.print("Merci de saisir le prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Merci de saisir le tel : ");
        String tel = scanner.nextLine();

        Contact contact = new Contact(firstName, lastName, tel);
        try {
            if(contact.save()) {
                System.out.println("Contact ajouté ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private void addEmailsAction(int contactId) {

        do {
            System.out.print("Ajouter un email ? (O/N) ");
            choix = scanner.nextLine();
            if(choix.equals("O")) {
                System.out.print("Merci de saisir le mail : ");
                String mail = scanner.nextLine();
                Email email = new Email(mail, contactId);
                try {
                    if(email.save()) {
                        System.out.println("Mail ajouté");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }while (!choix.equals("N"));
    }


    private void deleteContactByIdAction() {
        System.out.print("Merci de saisir l'id du contact : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Contact contact = Contact.getById(id);
            if(contact != null) {
                if(contact.delete()) {
                    System.out.println("Contact supprimé");
                }
            }
            else {
                System.out.println("Aucun contact avec cet id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

//    private void getByFirstName(){
//        System.out.print("Merci de saisir le nom : ");
//        String lastName = scanner.nextLine();
//
//    };

    private void editContactAction()  {
        System.out.println("**** Modifier un contact ****");
        System.out.print("Merci de saisir le nom : ");
        String lastName = scanner.nextLine();
        System.out.print("Merci de saisir le prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Merci de saisir le tel : ");
        String tel = scanner.nextLine();
        Contact contact = new Contact(firstName, lastName, tel);
        try {
            if(contact.save()) {
                System.out.println("Contact modifié ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void searchContactsAction() {
        System.out.print("Merci de saisir le mot de recherche : ");
        String word = scanner.nextLine();
        try {
            Contact.search(word).forEach(c-> {
                System.out.println(c);
                try {
                    c.setEmails(Email.getEmailsByContactId(c.getId()));
                    c.getEmails().forEach(e -> {
                        System.out.println(e);
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Contact getContactAction() {
        System.out.print("Merci de saisir l'id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Contact contact = Contact.getById(id);
            if (contact == null) {
                System.out.println("Aucun contact avec cet id");
            }
            return contact;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
