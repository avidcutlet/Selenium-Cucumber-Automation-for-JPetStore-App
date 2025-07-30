package com.test.demo_petstore.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature/",
    glue = {"com.cheq.demo_webshop.stepdefinitions", "com.cheq.demo_webshop.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags = "@Checkout"
)
public class TestRunner {
}

