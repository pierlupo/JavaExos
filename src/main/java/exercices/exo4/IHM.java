package exercices.exo4;

import exercices.exo4.dao.ProductDAO;
import exercices.exo4.model.Product;
import jdk.jshell.spi.ExecutionControl;
import org.example.util.DataBaseManager;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class IHM {

    Scanner scanner;
    String choix;

    private ProductDAO productDAO;
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
                    CreateProductAction();
                    break;
                case "2":
                    getProductAction();
                    break;
                case "3":
                    getAllProductAction();
                    break;
                case "4":
                    EditProductAction();
                    break;
                case "5":
                    DeleteProductAction();
                    break;

            }
        } while (!choix.equals("0"));
    }

    private void menu() {
        System.out.println("1 - Create product ");
        System.out.println("2 - Display product ");
        System.out.println("3 - Display All products ");
        System.out.println("4 - Edit Product ");
        System.out.println("5 - Delete product ");
        System.out.println("0 - Quit ");
    }

    private Product CreateProductAction() {
        Product product = null;
        System.out.print("Name of the product : ");
        String name = scanner.nextLine();
        System.out.print("Price of the product : ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Quantity : ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        product = new Product(name, price, quantity, description);
        try {
            connection = new DataBaseManager().getConnection();
            connection.setAutoCommit(false);
            productDAO = new ProductDAO(connection);
            if (productDAO.save(product)) {
                System.out.println("Product added " + product.getIdProduct());
            } else {
                product = null;
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            product = null;
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    private Product getProductAction() {
        Product product = null;
        System.out.print("Merci de saisir l'id : ");
        int idProduct = scanner.nextInt();
        scanner.nextLine();
        try {
            connection = new DataBaseManager().getConnection();
            productDAO = new ProductDAO(connection);
            product = productDAO.getById(idProduct);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
        }
        if (product != null) {
            System.out.println(product);
        }
        return product;
    }

    private List<Product> getAllProductAction() {
        List<Product> products = null;
        try {
            connection = new DataBaseManager().getConnection();
            productDAO = new ProductDAO(connection);
            products = productDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
        }
        if (products != null) {
            System.out.println(products);
        }
        return products;
    }


    private Product EditProductAction() {

        System.out.println("Merci de saisir l'id du produit à modifier");
        int id = scanner.nextInt();
        scanner.nextLine();
        Product produit = getProductAction();
        System.out.print("Merci de saisir le nom du produit : ");
        produit.setName(scanner.nextLine());
        System.out.print("Merci de saisir le prix du produit : ");
        produit.setPrice(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Merci de saisir la quantité des produits : ");
        produit.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Merci de saisir la description du produit : ");
        produit.setDescription(scanner.nextLine());
        try {
            connection = new DataBaseManager().getConnection();
            productDAO = new ProductDAO(connection);
            productDAO.update(produit);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Produit mis à jour");
        return produit;
    }


    private void DeleteProductAction(){

        System.out.print("Merci de saisir l'id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            connection = new DataBaseManager().getConnection();
            productDAO = new ProductDAO(connection);

                if(productDAO.delete(id)) {
                    System.out.println("Product erased");
                }

            else {
                System.out.println("No product with this id");
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
        }
    }
}