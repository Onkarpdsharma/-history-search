package com.bitcoin.history.service;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(classes = BitcoinPriceService.class)
class BitcoinPriceServiceTest {

    private BitcoinPriceService bitcoinPriceService;

    @BeforeEach
    void setUp() {
        bitcoinPriceService = new BitcoinPriceService();
    }

    @Test
    void testGetPricesOnline_ValidDateRange() {
        Map<String, Object> response = bitcoinPriceService.getPricesOnline("2025-03-01", "2025-03-16", "USD");
        
        assertNotNull(response);
        assertTrue(response.containsKey("prices"));
        assertTrue(response.containsKey("highest"));
        assertTrue(response.containsKey("lowest"));
        
        Map<String, Double> prices = (Map<String, Double>) response.get("prices");
        assertFalse(prices.isEmpty());
        assertEquals(41080.0, response.get("highest"));
        assertEquals(41000.0, response.get("lowest"));
    }

    @Test
    void testGetPricesOnline_InvalidDateRange() {
        Map<String, Object> response = bitcoinPriceService.getPricesOnline("2026-01-01", "2026-01-10", "USD");
        
        assertNotNull(response);
        assertTrue(response.containsKey("error"));
        assertEquals("No data available for the given dates", response.get("error"));
    }

    @Test
    void testGetPricesOnline_CurrencyConversion() {
        Map<String, Object> response = bitcoinPriceService.getPricesOnline("2025-03-01", "2025-03-16", "EUR");
        
        assertNotNull(response);
        assertTrue(response.containsKey("prices"));
        
        Map<String, Double> prices = (Map<String, Double>) response.get("prices");
        assertFalse(prices.isEmpty());
        assertEquals(41080.0 * 0.92, response.get("highest"));
        assertEquals(41000.0 * 0.92, response.get("lowest"));
    }
}
