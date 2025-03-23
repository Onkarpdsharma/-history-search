package com.bitcoin.history.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.bitcoin.history.entities.BitcoinPrice;

/**
 * In-memory implementation of PriceRepository, adhering to OCP for easy extension.
 */

@Repository
public class InMemoryPriceRepository implements PriceRepository {
    private static final Map<String, Double> historicalPrices = new HashMap<>();
    
    static {
    	    historicalPrices.put("2024-03-01", 42000.0);
	        historicalPrices.put("2024-03-02", 43000.0);
	        historicalPrices.put("2024-03-03", 41000.0);
	        historicalPrices.put("2024-03-04", 45000.0);
	        historicalPrices.put("2024-03-05", 44000.0);
	        historicalPrices.put("2024-03-01", 42000.0);
	        historicalPrices.put("2024-03-02", 43000.0);
	        historicalPrices.put("2025-03-01", 41000.0);
	        historicalPrices.put("2025-03-03", 41050.0);
	        historicalPrices.put("2025-03-05", 41060.0);
	        historicalPrices.put("2025-03-15", 41070.0);
	        historicalPrices.put("2025-03-16", 41080.0);
	        historicalPrices.put("2024-03-03", 42000.0);
	        historicalPrices.put("2024-03-03", 41000.0);
	        historicalPrices.put("2024-03-04", 45000.0);
	        historicalPrices.put("2024-03-05", 44000.0);
	        historicalPrices.put("2024-03-01", 42000.0);
	        historicalPrices.put("2025-01-02", 43000.0);
	        historicalPrices.put("2025-01-02", 41000.0);
	        historicalPrices.put("2025-01-01", 45000.0);
	        historicalPrices.put("2025-01-01", 44000.0);
    }


    
	//@Override  
    public List<BitcoinPrice> getPricesBetween(String start, String end) {
        return historicalPrices.entrySet().stream()
                .filter(entry -> entry.getKey().compareTo(start) >= 0 && entry.getKey().compareTo(end) <= 0)
                .map(entry -> new BitcoinPrice(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}