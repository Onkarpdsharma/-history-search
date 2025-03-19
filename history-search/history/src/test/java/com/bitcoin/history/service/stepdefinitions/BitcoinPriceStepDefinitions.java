package com.bitcoin.history.service.stepdefinitions;



import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.bitcoin.history.HistoryApplication;

import java.net.http.HttpHeaders;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = HistoryApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BitcoinPriceStepDefinitions {
	
	//private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	   @Autowired
	    private TestRestTemplate restTemplate;
	
	//   org.springframework.http.HttpHeaders headers = null;
	
//	protected String serverAddress = "http://localhost";
//	@Value("${local.server.port}")
//	protected int serverPort;
//	private String hostAddress;
	
//	@Before
//	public void runBeforAllTestMethods() {
//		
//		headers = new org.springframework.http.HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		hostAddress = serverAddress+ ":"+serverPort;
//		System.out.println(hostAddress);
//		
//	}

    private final String BASE_URL = "http://localhost:8080/api/bitcoin/price";
   
    private ResponseEntity<Map> response;

    @Given("the Bitcoin API is available")
    public void bitcoinApiIsAvailable() {
        // Assume the API is running and accessible
    }

    @When("I request prices from {string} to {string} in {string}")
    public void iRequestPrices(String start, String end, String currency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "?start=" + start + "&end=" + end + "&currency=" + currency;
        response = restTemplate.getForEntity(url, Map.class);
    }

    @Then("I should receive the following prices:")
    public void iShouldReceivePrices(io.cucumber.datatable.DataTable expectedPrices) {
        Map<String, Object> responseBody = response.getBody();
        Map<String, Double> actualPrices = (Map<String, Double>) responseBody.get("prices");

        expectedPrices.asMaps().forEach(row -> {
            String date = row.get("Date");
            double expectedPrice = Double.parseDouble(row.get("Price"));
            Assertions.assertEquals(expectedPrice, actualPrices.get(date), 0.1);
        });
    }

    @Then("the highest price should be {double}")
    public void highestPriceShouldBe(double expectedHighest) {
        Assertions.assertEquals(expectedHighest, response.getBody().get("highest"));
    }

    @Then("the lowest price should be {double}")
    public void lowestPriceShouldBe(double expectedLowest) {
        Assertions.assertEquals(expectedLowest, response.getBody().get("lowest"));
    }

    @Then("I should receive an error message {string}")
    public void iShouldReceiveErrorMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, response.getBody().get("error"));
    }

    @Given("the Bitcoin API is in offline mode")
    public void theBitcoinApiIsInOfflineMode() {
        // Ideally, you would mock the API to return offline mode responses
    }

    @Then("I should receive fallback prices:")
    public void iShouldReceiveFallbackPrices(io.cucumber.datatable.DataTable expectedPrices) {
        Map<String, Object> responseBody = response.getBody();
        Map<String, Double> actualPrices = (Map<String, Double>) responseBody.get("prices");

        expectedPrices.asMaps().forEach(row -> {
            String date = row.get("Date");
            double expectedPrice = Double.parseDouble(row.get("Price"));
            Assertions.assertEquals(expectedPrice, actualPrices.get(date), 0.1);
        });
    }
}
