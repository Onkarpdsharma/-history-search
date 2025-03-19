package com.bitcoin.history.controller;


import org.springframework.web.bind.annotation.*;

import com.bitcoin.history.service.BitcoinPriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.*;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

@Tag(name = "Bitcoin API", description = "Endpoints for fetching Bitcoin price data")
@RestController
@RequestMapping("/api/bitcoin")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend requests
public class BitcoinPriceController {


    private final BitcoinPriceService service; 
    private static final Logger logger = LogManager.getLogger(BitcoinPriceController.class);
    
    public BitcoinPriceController(BitcoinPriceService service) {
    	this.service = service;
    }
   
    @Operation(summary = "Get Bitcoin Prices", description = "Fetches historical Bitcoin prices")
    @GetMapping("/price")  
    Map<String, Object>  getBitcoinPricesOnline
    (@RequestParam String start, @RequestParam String end,
     @RequestParam(defaultValue = "USD")String currency) 
    
    {
    	 Map<String, Object> response = new HashMap<>();
    	 logger.info("price method geting invoked");
    	 response = service.getPricesOnline(start, end, currency);
    	 
    	 return response;
    	
    }

}

