package com.bitcoin.history.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitcoin.history.service.BitcoinPriceService;

@ExtendWith(MockitoExtension.class)
public class BitcoinPriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BitcoinPriceService bitcoinPriceService;

    @InjectMocks
    private BitcoinPriceController bitcoinPriceController;

    @Test
    public void testGetBitcoinPricesOnline_Success() throws Exception {
        // Setup mock response
        Map<String, Object> mockResponse = Collections.singletonMap("prices", Collections.emptyMap());
        EntityModel<Map<String, Object>> entityModel = EntityModel.of(mockResponse);

        when(bitcoinPriceService.getPricesOnline("2024-03-01", "2024-03-05", "USD"))
                .thenReturn(entityModel);

        // Setup MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bitcoinPriceController).build();

        // Perform GET request and verify response
        mockMvc.perform(get("/api/bitcoin/price")
                .param("start", "2024-03-01")
                .param("end", "2024-03-05")
                .param("currency", "USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prices").exists());
    }
}

