package com.portfolio.model;

import java.util.*;

public class Portfolio {
    private List<Stock> stocks = new ArrayList<>();

    public List<Stock> getStocks() { return stocks; }

    public void addStock(Stock s) { stocks.add(s); }

    public void removeStock(String name) {
        stocks.removeIf(s -> s.name.equalsIgnoreCase(name));
    }

    public Stock getStock(String name) {
        return stocks.stream().filter(s -> s.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
