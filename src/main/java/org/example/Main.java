package org.example;

import designPattern.Builder.Person;
import designPattern.Builder.exercice.Book;
import designPattern.abstractFactory.exerciceAbsFac.HumainFactory;
import designPattern.abstractFactory.exerciceAbsFac.Jeu;
import designPattern.abstractFactory.exerciceAbsFac.OrcFactory;

public class Main {
    public static void main(String[] args) {
//        DataBaseManager dataBaseManager = new DataBaseManager();
//        Scanner scanner = new Scanner(System.in);
//        try {
//            Connection connection = dataBaseManager.getConnection();
//            System.out.println("Connexion ok");
//            System.out.println("Merci de saisir le prénom : ");
//            String firstName = scanner.nextLine();
//            System.out.println("Merci de saisir le nom : ");
//            String lastName = scanner.nextLine();
        //Une requête pour l'insertion de données
        //String request = "INSERT INTO personne(first_name, last_name) VALUES ('"+firstName+"', '"+lastName+"')";
        //String request = "INSERT INTO personne(first_name, last_name) VALUES (?, ?)";

        //un objet qui respecte l'interface statement est un un objet qui permet l'execution des requêtes sql
        //Statement statement = connection.createStatement();

        //Façon 1 => execution de requête sans retour
//            boolean res = statement.execute(request);
//            System.out.println("Requête exécutée");
//            if(res){
//                System.out.println("données renvoyées par la requête");
//            }
//            else {
//                System.out.println("Aucune données renvoyées par la requête");
//            }

        //Façon 2 => avec requête préparée
//            PreparedStatement preparedStatement = connection.prepareStatement(request);
//            preparedStatement.setString(1,firstName);
//            preparedStatement.setString(2,lastName);
//            //preparedStatement.execute();
//            int nbRows = preparedStatement.executeUpdate();
//            System.out.println("nombre de ligne(s) affectée(s): "+nbRows);

        //Façon 3 => avec requête de lecture


//            String request = "SELECT * FROM personne";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(request);
//            while (resultSet.next()) {
//                System.out.println("ID : "+resultSet.getInt("personne_id") + "," + " Prénom : "+ resultSet.getString( "first_name")+ ","+ " Nom : " +resultSet.getString( "last_name"));
//            }
//
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
// Exercice1.Demo();


        //new IHM().start();
        //exo 3
        //new IHM().start();
        //exo3bis
        //new IHM().start();
        //exo3DAO
        //new IHM().start();
        //exo4
        //new IHM().start();
        //exo5
        //new IHM().start();

        //exo abstract factory
//        Archer archer1 = new Archer(new HumainFactory());
//        archer1.tirerAlArc();
//        Archer archer2 = new Archer(new OrcFactory());
//        archer2.tirerAlArc();

//        Jeu jeu1 = new Jeu(new HumainFactory());
//        jeu1.tirerAlArc();
//        jeu1.CombattreCorpsaCorps();
//        jeu1.charger();
//        Jeu jeu2 = new Jeu(new OrcFactory());
//        jeu2.tirerAlArc();
//        jeu2.CombattreCorpsaCorps();
//        jeu2.charger();

        Book b1 = new Book.Builder().title("t1").author("c 1").build();
        Book b2 = new Book.Builder().title("t2").publishingYear("1900").build();
        System.out.println(b1);
        System.out.println(b2);


    }

}