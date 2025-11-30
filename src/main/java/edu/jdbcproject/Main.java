package edu.jdbcproject;
import edu.jdbcproject.config.Helper;
import edu.jdbcproject.dao.ProductDao;
import edu.jdbcproject.dao.impl.ProductDaoImpl;
import edu.jdbcproject.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.IO.println;

public class Main {
    static void main() {
        println("Hello World!");
        try {
            println(Helper.makeCon());
        } catch (SQLException e) {
            println(e);
        }
        Scanner sc = new Scanner(System.in);
        ProductDao dao = new ProductDaoImpl();

        while (true) {
            System.out.println("\n===== PRODUCT MENU =====");
            System.out.println("1. Add Product");
            System.out.println("2. Get Product by ID");
            System.out.println("3. List all Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1-> {
                    // Add product
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Product Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Product Quantity: ");
                    int qty = sc.nextInt();

                    Product p = new Product(id, name, price, qty);
                    dao.addProduct(p);
                    System.out.println("Product added successfully.");
                }

                case 2-> {
                    // Get product by id
                    System.out.print("Enter Product ID: ");
                    int gid = sc.nextInt();
                    Product gp = dao.getProduct(gid);
                    System.out.println(gp);
                }

                case 3-> {
                    // List all products
                    List<Product> products = dao.getAllProducts();
                    products.forEach(System.out::println);
                }

                case 4-> {
                    // Update product
                    System.out.print("Enter Product ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new Product Name: ");
                    String uname = sc.nextLine();

                    System.out.print("Enter new Product Price: ");
                    double uprice = sc.nextDouble();

                    System.out.print("Enter new Product Quantity: ");
                    int uq = sc.nextInt();

                    Product up = new Product(uid, uname, uprice, uq);
                    dao.updateProduct(up);
                    System.out.println("Product updated successfully.");
                }

                case 5-> {
                    // Delete product
                    System.out.print("Enter Product ID to delete: ");
                    int did = sc.nextInt();
                    dao.deleteProduct(did);
                    System.out.println("Product deleted successfully.");
                }

                case 6->{
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                }

                default->
                    System.out.println("Invalid choice, try again.");
            }

        }
    }
}

