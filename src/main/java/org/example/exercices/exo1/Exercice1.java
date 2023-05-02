package org.example.exercices.exo1;

import java.sql.*;
import java.util.Scanner;

public class Exercice1 {
    public static void Demo() {

        DataBaseExo1Manager dataBaseExo1Manager = new DataBaseExo1Manager();
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = dataBaseExo1Manager.getConnection();
            System.out.println("Connexion ok");

            //Ajouter des étudiants
//            System.out.println("Merci de saisir le nom : ");
//            String nom = scanner.nextLine();
//            System.out.println("Merci de saisir le prénom : ");
//            String prenom = scanner.nextLine();
//            System.out.println("Merci de saisir le numéro de classe : ");
//            Integer numClasse = Integer.valueOf(scanner.nextLine());
//            System.out.println("Merci de saisir la date d'obtention du diplome: ");
//            String dateDiplome = scanner.nextLine();
//            String request = "INSERT INTO etudiant(nom, prenom, numero_classe, date_diplome) VALUES (?, ?, ?, ?)";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(request);
//            preparedStatement.setString(1,nom);
//            preparedStatement.setString(2,prenom);
//            preparedStatement. setInt(3,numClasse);
//            preparedStatement. setString(4,dateDiplome);
//            preparedStatement.execute();

            //Afficher tous les étudiants
//            String request = "SELECT * FROM etudiant";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(request);
//            while (resultSet.next()) {
//                System.out.println("ID : "+resultSet.getInt("etudiant_id") + "," + " nom : "+ resultSet.getString( "nom")+ ","+ " Prénom : " +resultSet.getString( "prenom")+ "," +resultSet.getInt("numero_classe") + ","+ resultSet.getString( "date_diplome"));
//            }

            //Afficher les étudiants d'une classe
//            String request = "SELECT * FROM etudiant WHERE numero_classe = 3";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(request);
//            while (resultSet.next()) {
//                System.out.println("ID : "+resultSet.getInt("etudiant_id") + "," + " nom : "+ resultSet.getString( "nom")+ ","+ " Prénom : " +resultSet.getString( "prenom")+ "," +resultSet.getInt("numero_classe") + ","+ resultSet.getString( "date_diplome"));
//            }

            //Supprimer un étudiant par l'id
            System.out.println("Merci de saisir l'id de l'étudiant' : ");
            String delete = scanner.nextLine();
            String request = "DELETE FROM etudiant WHERE etudiant_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, Integer.parseInt(delete));
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

