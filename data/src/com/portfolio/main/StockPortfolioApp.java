package com.portfolio.main;

import com.portfolio.service.*;
import com.portfolio.model.*;
import java.util.*;

public class StockPortfolioApp {

    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static PortfolioService portfolioService = new PortfolioService();
    private static AlertService alertService = new AlertService();
    private static PriceUpdater priceUpdater;

    public static void main(String[] args) {

        System.out.println("===== SMART STOCK PORTFOLIO SYSTEM =====");

        User loggedUser = null;
        while (loggedUser == null) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            System.out.print("Enter option: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> loggedUser = login();
                case 2 -> loggedUser = register();
                case 3 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }

        // Start background price updater thread
        priceUpdater = new PriceUpdater(loggedUser.getUsername());
        priceUpdater.start();

        // Main menu
        while (true) {
            System.out.println("\n===== PORTFOLIO MENU =====");
            System.out.println("""
                1. Add Stock
                2. View Portfolio
                3. Remove Stock
                4. Update Stock Quantity
                5. Set Price Alert
                6. Generate Profit/Loss Report
                7. Exit
            """);
            System.out.print("Enter option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> portfolioService.addStock(loggedUser.getUsername());
                case 2 -> portfolioService.viewPortfolio(loggedUser.getUsername());
                case 3 -> portfolioService.removeStock(loggedUser.getUsername());
                case 4 -> portfolioService.updateQuantity(loggedUser.getUsername());
                case 5 -> alertService.setAlert(loggedUser.getUsername());
                case 6 -> portfolioService.generateReport(loggedUser.getUsername());
                case 7 -> {
                    System.out.println("Saving & exiting...");
                    priceUpdater.stopUpdater();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static User login() {
        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();

        User user = userService.login(u, p);
        if (user == null) {
            System.out.println("❌ Login failed!");
            return null;
        }
        System.out.println("✅ Login successful!");
        return user;
    }

    private static User register() {
        System.out.print("Choose username: ");
        String u = sc.next();
        System.out.print("Choose password: ");
        String p = sc.next();

        boolean ok = userService.register(u, p);
        if (!ok) {
            System.out.println("❌ Username already exists!");
            return null;
        }
        System.out.println("✅ Registration done!");
        return new User(u, p);
    }
}
