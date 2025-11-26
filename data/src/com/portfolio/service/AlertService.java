package com.portfolio.service;

import com.portfolio.util.*;

import java.util.*;

public class AlertService {

    Scanner sc = new Scanner(System.in);

    public void setAlert(String username) {
        System.out.print("Stock Name: ");
        String name = sc.next();
        System.out.print("Alert Price: ");
        double ap = sc.nextDouble();

        List<String[]> rows = CSVReader.read("data/portfolio_" + username + ".csv");

        for (String[] r : rows) {
            if (r[0].equalsIgnoreCase(name)) {
                r[4] = ap + "";
            }
        }

        CSVWriter.writeAll("data/portfolio_" + username + ".csv", rows);
        System.out.println("🔔 Alert set.");
    }
}
