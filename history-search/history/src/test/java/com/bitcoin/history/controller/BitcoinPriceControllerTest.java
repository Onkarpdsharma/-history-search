package com.bitcoin.history.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitcoin.history.controller.BitcoinPriceController;
import com.bitcoin.history.service.BitcoinPriceService;

@ExtendWith(MockitoExtension.class)
class BitcoinPriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BitcoinPriceService bitcoinPriceService;

    @InjectMocks
    private BitcoinPriceController bitcoinPriceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bitcoinPriceController).build();
    }

    @Test
    void testGetBitcoinPricesOnline() throws Exception {
        // Mock response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("price", 50000);

        when(bitcoinPriceService.getPricesOnline("2024-01-01", "2024-01-10", "USD"))
                .thenReturn(mockResponse);

        mockMvc.perform(get("/api/bitcoin/price")
                .param("start", "2024-01-01")
                .param("end", "2024-01-10")
                .param("currency", "USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(50000));

        verify(bitcoinPriceService, times(1)).getPricesOnline("2024-01-01", "2024-01-10", "USD");
    }
}
