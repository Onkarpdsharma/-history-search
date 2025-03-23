package com.bitcoin.history.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;

import com.bitcoin.history.entities.BitcoinPrice;
import com.bitcoin.history.repository.InMemoryPriceRepository;
import com.bitcoin.history.repository.PriceRepository;
import com.bitcoin.history.util.CurrencyConverter;



@ExtendWith(MockitoExtension.class)
class BitcoinPriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    
    @Mock
    private InMemoryPriceRepository inMemoryPriceRepository;
    
    @Mock
    private CurrencyConverter currencyConverter;
    
    @InjectMocks
    private BitcoinPriceService bitcoinPriceService;
    
    private List<BitcoinPrice> samplePrices;
    
    @BeforeEach
    void setUp() {
        samplePrices = Arrays.asList(
            new BitcoinPrice("2025-03-01", 41000.0),
            new BitcoinPrice("2025-03-02", 42000.0),
            new BitcoinPrice("2025-03-03", 43000.0)
        );
    }
    
    @Test
    void testGetPricesOnlineWithValidData() {
        when(inMemoryPriceRepository.getPricesBetween("2025-03-01", "2025-03-03")).thenReturn(samplePrices);
        when(currencyConverter.getConversionRate("USD")).thenReturn(1.0);
        
        EntityModel<Map<String, Object>> result = bitcoinPriceService.getPricesOnline("2025-03-01", "2025-03-03", "USD");
        
        Map<String, Object> response = result.getContent();
        assertNotNull(response);
        assertEquals("2025-03-01", response.get("start_date"));
        assertEquals("2025-03-03", response.get("end_date"));
        assertEquals(43000.0, response.get("highest"));
        assertEquals(41000.0, response.get("lowest"));
    }
    
    @Test
    void testGetPricesOnlineWithNoData() {
        when(inMemoryPriceRepository.getPricesBetween("2025-03-10", "2025-03-12")).thenReturn(Collections.emptyList());
        
        EntityModel<Map<String, Object>> result = bitcoinPriceService.getPricesOnline("2025-03-10", "2025-03-12", "USD");
        
        Map<String, Object> response = result.getContent();
        assertNotNull(response);
        assertTrue(response.containsKey("error"));
        assertEquals("No data available for the given dates", response.get("error"));
    }
}

