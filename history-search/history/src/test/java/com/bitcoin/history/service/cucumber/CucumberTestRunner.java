package com.bitcoin.history.service.cucumber;



import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "com.bitcoin.history.service.steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json")
@ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "false")
@ConfigurationParameter(key = "cucumber.execution.strict", value = "true")
public class CucumberTestRunner {
}
