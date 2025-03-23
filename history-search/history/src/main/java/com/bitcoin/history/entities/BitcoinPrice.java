package com.bitcoin.history.entities;

import org.springframework.stereotype.Component;

/**
 * BitcoinPrice represents a historical price entry.
 */
//@Component
public class BitcoinPrice {
    private final String date;
    private final double price;

    public BitcoinPrice(String date, double price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}