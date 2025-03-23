package com.bitcoin.history;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

//import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


//@Suite
//@IncludeEngines("cucumber")
//@SelectClasspathResource("features")  // Path to your feature files
//@ConfigurationParameter(key = "cucumber.glue", value = "com.bitcoin.history.service.stepdefinition")  // Path to step definitions
//@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json")

@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {"pretty", "json:target/cucumber-report.json"}, tags = "not @ignore")
public class CucumberIntegrationTest {
	
	@Test
	void contextLoads() {
		
	}

}
