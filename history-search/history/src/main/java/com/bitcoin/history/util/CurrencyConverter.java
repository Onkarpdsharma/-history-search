package com.bitcoin.history.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Service for converting currencies, following Singleton pattern.
 */
@Component
public class CurrencyConverter {
    private static final Map<String, Double> conversionRates = new HashMap<String, Double>();
    
    static {
        conversionRates.put("EUR", 0.92);
        conversionRates.put("GBP", 0.79);
        conversionRates.put("INR", 83.5);
        conversionRates.put("USD", 1.0);
    }
    
    public double getConversionRate(String currency) {
        return conversionRates.getOrDefault(currency, 1.0);
    }
}
