package com.test.demo_petstore.hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	public static WebDriver driver;
	
	@Before
	public void setUp() {
		// Initialize WebDriver, e.g., ChromeDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://petstore.octoperf.com/");
	}
	
	@After
	public void tearDown() {
		// Quit the WebDriver instance
		if (driver != null) {
			driver.quit();
		}
	}

}
