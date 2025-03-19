package com.bitcoin.history.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/service")
//@CucumberOptions(features = "classpath:features", /* plugin = {"pretty","json:target/cucumber-report.json"}, */ tags ="not @ignore")
//@SpringBootTest
@SpringBootTest
@ContextConfiguration(classes = {BitcoinPriceService.class})
public class CucumberIntegrationTest {
	
	@Test
	void contextLoads() {
		
	}

}
