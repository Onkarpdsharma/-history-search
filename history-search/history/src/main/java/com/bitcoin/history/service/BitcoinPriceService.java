package com.bitcoin.history.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.bitcoin.history.entities.BitcoinPrice;
import com.bitcoin.history.repository.InMemoryPriceRepository;
import com.bitcoin.history.repository.PriceRepository;
import com.bitcoin.history.util.CurrencyConverter;





/**
 * BitcoinPriceService provides historical Bitcoin prices with currency conversion
 * and adheres to SOLID principles, 12-Factor methodology, and HATEOAS principles.
 */
@Service
public class BitcoinPriceService {
    private static final Logger logger = LogManager.getLogger(BitcoinPriceService.class);
    private final PriceRepository priceRepository;
    private final InMemoryPriceRepository imMemoryPriceRepo;
    private final CurrencyConverter currencyConverter;

    /**
     * Constructor for BitcoinPriceService, following Dependency Injection (DIP).
     * @param priceRepository Repository for fetching price data.
     * @param currencyConverter Service for currency conversion.
     */
    public BitcoinPriceService(PriceRepository priceRepository, CurrencyConverter  currencyConverter
    		,InMemoryPriceRepository imMemoryPriceRepo) {
        this.priceRepository = priceRepository;
        this.currencyConverter = currencyConverter;
        this.imMemoryPriceRepo = imMemoryPriceRepo;
   
    }

    /**
     * Fetches Bitcoin prices between the given start and end dates, converts to the requested currency,
     * and provides HATEOAS links.
     * @param start Start date.
     * @param end End date.
     * @param currency Currency for conversion.
     * @return EntityModel containing price details and HATEOAS links.
     */
    public EntityModel<Map<String, Object>> getPricesOnline(String start, String end, String currency) {
        logger.info("Fetching Bitcoin prices from {} to {} in currency: {}", start, end, currency);

        List<BitcoinPrice> filteredPrices = imMemoryPriceRepo.getPricesBetween(start, end);
        if (filteredPrices.isEmpty()) {
            return EntityModel.of(Collections.singletonMap("error", "No data available for the given dates"));
        }

        double highest = filteredPrices.stream().mapToDouble(BitcoinPrice::getPrice).max().orElse(0);
        double lowest = filteredPrices.stream().mapToDouble(BitcoinPrice::getPrice).min().orElse(0);

        double conversionRate = currencyConverter.getConversionRate(currency);
        Map<String, Double> convertedPrices = filteredPrices.stream()
                .collect(Collectors.toMap(BitcoinPrice::getDate, price -> price.getPrice() * conversionRate));

        Map<String, Object> response = new HashMap<>();
        response.put("start_date", start);
        response.put("end_date", end);
        response.put("currency", currency);
        response.put("prices", convertedPrices);
        response.put("highest", highest * conversionRate);
        response.put("lowest", lowest * conversionRate);
        
        EntityModel<Map<String, Object>> entityModel = EntityModel.of(response);
        entityModel.add(Link.of("/prices?start=" + start + "&end=" + end, "self"));
        return entityModel;
    }
}



//@Service
//public class BitcoinPriceService {
//	
//    private static final Logger logger = LogManager.getLogger(BitcoinPriceService.class);
//	
//	 private static final Map<String, Double> historicalPrices = new HashMap<>();
//	    static {
//	        historicalPrices.put("2024-03-01", 42000.0);
//	        historicalPrices.put("2024-03-02", 43000.0);
//	        historicalPrices.put("2024-03-03", 41000.0);
//	        historicalPrices.put("2024-03-04", 45000.0);
//	        historicalPrices.put("2024-03-05", 44000.0);
//	        historicalPrices.put("2024-03-01", 42000.0);
//	        historicalPrices.put("2024-03-02", 43000.0);
//	        historicalPrices.put("2025-03-01", 41000.0);
//	        historicalPrices.put("2025-03-03", 41050.0);
//	        historicalPrices.put("2025-03-05", 41060.0);
//	        historicalPrices.put("2025-03-15", 41070.0);
//	        historicalPrices.put("2025-03-16", 41080.0);
//	        historicalPrices.put("2024-03-03", 42000.0);
//	        historicalPrices.put("2024-03-03", 41000.0);
//	        historicalPrices.put("2024-03-04", 45000.0);
//	        historicalPrices.put("2024-03-05", 44000.0);
//	        historicalPrices.put("2024-03-01", 42000.0);
//	        historicalPrices.put("2025-01-02", 43000.0);
//	        historicalPrices.put("2025-01-02", 41000.0);
//	        historicalPrices.put("2025-01-01", 45000.0);
//	        historicalPrices.put("2025-01-01", 44000.0);
//	        logger.info("historical price in service");
//	    }
//
//	    // Simulated currency conversion rates
//	    private static final Map<String, Double> conversionRates = new HashMap<>();
//	    static {
//	        conversionRates.put("EUR", 0.92);
//	        conversionRates.put("GBP", 0.79);
//	        conversionRates.put("INR", 83.5);
//	        conversionRates.put("USD", 1.0);
//	        logger.info("conversion rate in service");
//	    }
//	
//	public  Map<String, Object> getPricesOnline(String start, String end,String currency)
//	{
//		  Map<String, Object> response = new HashMap<>();
//
//	       
//	        // Convert input dates
//	        Date startDate = java.sql.Date.valueOf(start);
//	        Date endDate = java.sql.Date.valueOf(end);
//
//	        // Filter data
//	        Map<String, Double> filteredPrices = new TreeMap<>();
//	        for (String date : historicalPrices.keySet()) {
//	            Date currentDate = java.sql.Date.valueOf(date);
//	            if (!currentDate.before(startDate) && !currentDate.after(endDate)) {
//	                filteredPrices.put(date, historicalPrices.get(date));
//	            }
//	        }
//
//	        if (filteredPrices.isEmpty()) {
//	            response.put("error", "No data available for the given dates");
//	            return response;
//	        }
//
//	        // Find highest & lowest prices
//	        double highest = Collections.max(filteredPrices.values());
//	        double lowest = Collections.min(filteredPrices.values());
//
//	        // Convert to selected currency
//	        double conversionRate = conversionRates.getOrDefault(currency, 1.0);
//	        Map<String, Double> convertedPrices = new TreeMap<>();
//	        for (Map.Entry<String, Double> entry : filteredPrices.entrySet()) {
//	            convertedPrices.put(entry.getKey(), entry.getValue() * conversionRate);
//	        }
//
//	        // Prepare response
//	        response.put("start_date", start);
//	        response.put("end_date", end);
//	        response.put("currency", currency);
//	        response.put("prices", convertedPrices);
//	        response.put("highest", highest * conversionRate);
//	        response.put("lowest", lowest * conversionRate);
//	        logger.info("conversion rate is executed");
//	        return response;
//	}
//
//}
