package com.bitcoin.history.service.steps;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;

import com.bitcoin.history.HistoryApplication;
import com.bitcoin.history.entities.BitcoinPrice;
import com.bitcoin.history.repository.InMemoryPriceRepository;
import com.bitcoin.history.service.BitcoinPriceService;
import com.bitcoin.history.util.CurrencyConverter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest(classes = HistoryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BitcoinPriceSteps {
    @Mock private InMemoryPriceRepository inMemoryPriceRepository;
    @Mock private CurrencyConverter currencyConverter;
    private BitcoinPriceService bitcoinPriceService;
    private EntityModel<Map<String, Object>> response;
    
    @Given("Bitcoin price data exists between {string} and {string}")
    public void bitcoin_price_data_exists_between(String start, String end) {
        when(inMemoryPriceRepository.getPricesBetween(start, end)).thenReturn(Arrays.asList(
            new BitcoinPrice("2025-03-01", 41000.0),
            new BitcoinPrice("2025-03-02", 42000.0),
            new BitcoinPrice("2025-03-03", 43000.0)
        ));
        when(currencyConverter.getConversionRate("USD")).thenReturn(1.0);
    }

    @Given("no Bitcoin price data exists between {string} and {string}")
    public void no_bitcoin_price_data_exists_between(String start, String end) {
        when(inMemoryPriceRepository.getPricesBetween(start, end)).thenReturn(Collections.emptyList());
    }

    @When("the client requests Bitcoin prices from {string} to {string} in {string}")
    public void the_client_requests_Bitcoin_prices(String start, String end, String currency) {
        response = bitcoinPriceService.getPricesOnline(start, end, currency);
    }

    @Then("the response should contain the highest price {double} and lowest price {double}")
    public void the_response_should_contain_highest_and_lowest(double highest, double lowest) {
        Map<String, Object> content = response.getContent();
        assertNotNull(content);
        assertEquals(highest, content.get("highest"));
        assertEquals(lowest, content.get("lowest"));
    }

    @Then("the response should contain an error message {string}")
    public void the_response_should_contain_error(String errorMessage) {
        Map<String, Object> content = response.getContent();
        assertNotNull(content);
        assertTrue(content.containsKey("error"));
        assertEquals(errorMessage, content.get("error"));
    }
}
