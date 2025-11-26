package com.portfolio.service;

import com.portfolio.util.*;

import java.util.*;

public class PriceUpdater extends Thread {

    private boolean running = true;
    private String username;

    public PriceUpdater(String user) {
        this.username = user;
    }

    @Override
    public void run() {
        while (running) {
            try {
                List<String[]> rows = CSVReader.read("data/portfolio_" + username + ".csv");

                Random rand = new Random();

                for (String[] r : rows) {
                    double current = Double.parseDouble(r[3]);
                    double change = (rand.nextDouble() * 20) - 10;
                    r[3] = (current + change) + "";

                    // Alerts
                    double alertPrice = Double.parseDouble(r[4]);
                    if (alertPrice > 0 && Double.parseDouble(r[3]) >= alertPrice) {
                        System.out.println("⚠ ALERT: " + r[0] + " hit your target price!");
                    }
                }

                CSVWriter.writeAll("data/portfolio_" + username + ".csv", rows);

                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("Updater stopped.");
            }
        }
    }

    public void stopUpdater() {
        running = false;
    }
}
