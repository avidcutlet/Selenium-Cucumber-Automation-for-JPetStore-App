package com.test.demo_petstore.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class ElementActionUtils {
 
	private WebDriver driver;
	private WebDriverWait wait;
	private ScreenshotUtil screenshotUtil;
	
	public ElementActionUtils (WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.screenshotUtil = new ScreenshotUtil(driver);
		
	}
	
	public void clickElement(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			System.out.println("Clicked on element:" + locator.toString());
			screenshotUtil.takeScreenshot("click_element");
		}
		catch (Exception e) {
			System.out.println("Failed to click on element:" + locator.toString());
			screenshotUtil.takeScreenshot("failed_click_element");
		}
		
	}
	
	public void changeDropdownValue(By locator, String value) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			Select select = new Select(driver.findElement(locator));
			select.selectByValue(value);
			System.out.println("Change value on element:" + locator.toString());
			screenshotUtil.takeScreenshot("change_value_element");
		}
		catch (Exception e) {
			System.out.println("Failed to change value on element:" + locator.toString());
			screenshotUtil.takeScreenshot("failed_change_value_element");
		}
	}
	
	public void inputElement(By locator, String text) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.sendKeys(text);
			System.out.println("Entered text on element:" + locator.toString());
			screenshotUtil.takeScreenshot("input_element");
		}
		catch (Exception e) {
			System.out.println("Failed to enter text on element:" + locator.toString());
			screenshotUtil.takeScreenshot("failed_input_element");
		}
	}
	
	public void removeInput(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.clear();
			System.out.println("Remove text on element:" + locator.toString());
			screenshotUtil.takeScreenshot("remove_text_element");
		}
		catch (Exception e) {
			System.out.println("Failed to remove text on element:" + locator.toString());
			screenshotUtil.takeScreenshot("failed_remove_text_element");
		}
	}
	
	public void verifyDisplayed(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.isDisplayed();
			System.out.println("Element is displayed:" + locator.toString());
			screenshotUtil.takeScreenshot("element_displayed");
		}
		catch (Exception e) {
			System.out.println("Element is not displayed:" + locator.toString());
			screenshotUtil.takeScreenshot("element_not_displayed");
		}
			
	}
	
	public void getTextAndCompare(By locator, String expectedText) {
	    try {
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        String actualText = element.getText().trim();

	        if (actualText.contains(expectedText)) {
	            System.out.println("Text matches expected: " + expectedText);
	            screenshotUtil.takeScreenshot("expectedText_displayed");
	        } else {
	            System.out.println("Text does not match. Found: " + actualText + ", Expected: " + expectedText);
	            screenshotUtil.takeScreenshot("expectedText_not_displayed");
	            throw new AssertionError("Text does not match expected");
	        }

	    } catch (Exception e) {
	        System.out.println("Element is not displayed or error occurred: " + locator.toString());
	        screenshotUtil.takeScreenshot("element_did_not_match_expected");
	        throw e;
	    }
	}
	
	public Boolean assertIfContainsName(String actualText) {
        return actualText.contains("Name");
	}
}