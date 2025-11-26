package com.portfolio.service;

import com.portfolio.model.Stock;
import com.portfolio.util.CSVReader;
import com.portfolio.util.CSVWriter;

import java.util.*;

public class PortfolioService {

    Scanner sc = new Scanner(System.in);

    private String getPortfolioFile(String username) {
        return "data/portfolio_" + username + ".csv";
    }

    // Ensure portfolio file exists
    private void ensurePortfolioFile(String username) {
        String file = getPortfolioFile(username);
        CSVWriter.ensureFile(file);
    }

    public void addStock(String username) {
        ensurePortfolioFile(username);

        System.out.print("Stock Name: ");
        String name = sc.next();
        System.out.print("Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Buy Price: ");
        double bp = sc.nextDouble();

        double cp = bp;  // Starting current price

        CSVWriter.append(getPortfolioFile(username),
                new String[]{name, qty + "", bp + "", cp + "", "0"});

        System.out.println(" Stock added.");
    }

    public void viewPortfolio(String username) {
        ensurePortfolioFile(username);

        List<String[]> rows = CSVReader.read(getPortfolioFile(username));

        if (rows.size() == 0) {
            System.out.println("Portfolio empty.");
            return;
        }

        System.out.println("\n===== PORTFOLIO =====");
        System.out.printf("%-10s %-8s %-10s %-12s %-10s\n", "STOCK", "QTY", "BUY PRICE", "CURRENT PRICE", "ALERT");
        System.out.println("----------------------------------------------------");

        for (String[] r : rows) {
            System.out.printf("%-10s %-8s %-10s %-12s %-10s\n", r[0], r[1], r[2], r[3], r[4]);
        }
    }

    public void removeStock(String username) {
        ensurePortfolioFile(username);

        System.out.print("Enter stock name to remove: ");
        String name = sc.next();

        List<String[]> rows = CSVReader.read(getPortfolioFile(username));
        rows.removeIf(r -> r[0].equalsIgnoreCase(name));
        CSVWriter.writeAll(getPortfolioFile(username), rows);

        System.out.println(" Stock removed.");
    }

    public void updateQuantity(String username) {
        ensurePortfolioFile(username);

        System.out.print("Stock Name: ");
        String name = sc.next();
        System.out.print("New Quantity: ");
        int q = sc.nextInt();

        List<String[]> rows = CSVReader.read(getPortfolioFile(username));
        boolean updated = false;
        for (String[] r : rows) {
            if (r[0].equalsIgnoreCase(name)) {
                r[1] = q + "";
                updated = true;
            }
        }
        CSVWriter.writeAll(getPortfolioFile(username), rows);

        if (updated) System.out.println(" Quantity updated.");
        else System.out.println(" Stock not found.");
    }

    public void generateReport(String username) {
        ensurePortfolioFile(username);

        List<String[]> rows = CSVReader.read(getPortfolioFile(username));

        if (rows.size() == 0) {
            System.out.println("Portfolio empty. No report available.");
            return;
        }

        double totalProfit = 0;
        System.out.println("\n===== PROFIT / LOSS REPORT =====");
        System.out.printf("%-10s %-8s %-12s %-12s %-12s\n", "STOCK", "QTY", "BUY PRICE", "CURRENT PRICE", "P/L");

        for (String[] r : rows) {
            int qty = Integer.parseInt(r[1]);
            double buy = Double.parseDouble(r[2]);
            double cur = Double.parseDouble(r[3]);
            double profit = (cur - buy) * qty;
            totalProfit += profit;

            System.out.printf("%-10s %-8d %-12.2f %-12.2f %-12.2f\n", r[0], qty, buy, cur, profit);
        }

        System.out.println("----------------------------------------------------");
        System.out.printf("Total Profit/Loss: %.2f\n", totalProfit);
    }
}
