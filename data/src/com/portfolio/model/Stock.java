package com.portfolio.model;

public class Stock {
    public String name;
    public int qty;
    public double buyPrice;
    public double currentPrice;
    public double alertPrice;

    public Stock(String name, int qty, double buyPrice, double currentPrice, double alertPrice) {
        this.name = name;
        this.qty = qty;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.alertPrice = alertPrice;
    }
}
