package com.test.demo_petstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {

    private WebDriver driver;

    private final By ORDER_CONFIRMATION_HEADER = By.xpath("//h2[contains(text(),'Thank you') or contains(text(),'Order')]");
    private final By ORDER_ID_TEXT = By.xpath("//b[contains(text(),'Order')]/following-sibling::font");
    private final By CONTINUE_BUTTON = By.xpath("//a[contains(text(),'Continue')]");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderConfirmationMessage() {
        return driver.findElement(ORDER_CONFIRMATION_HEADER).getText().trim();
    }

    public String getOrderId() {
        return driver.findElement(ORDER_ID_TEXT).getText().trim();
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }
}
