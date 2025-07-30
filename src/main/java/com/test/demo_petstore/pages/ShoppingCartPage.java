package com.test.demo_petstore.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.demo_petstore.utils.ElementActionUtils;


public class ShoppingCartPage {
	private WebDriver driver;
   
private ElementActionUtils elementActionUtils;


    private By CART_TTL = By.xpath("//h2[text()='Shopping Cart']");
    private By UPDATE_CART_BTN = By.xpath("//input[@name='updateCartQuantities']");
    private By SHOPPING_CART_BTN = By.xpath("//img[@name='img_cart']");
    private By PROCEED_TO_CHECKOUT_BTN = By.xpath("//a[text()='Proceed to Checkout']");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void verifyCartPageIsDisplayed() {
        elementActionUtils.verifyDisplayed(CART_TTL);
    }

    public void updateQuantity(String productName, String itemID, String quantity) {
    	By quantityLocator = By.xpath("//td[contains(normalize-space(.), '"+ productName +"')]/following-sibling::td/input[@name='"+ itemID +"']");
    	elementActionUtils.removeInput(quantityLocator);
    	elementActionUtils.inputElement(quantityLocator, quantity);
    	elementActionUtils.clickElement(UPDATE_CART_BTN);
    }
    
    public void removeProduct(String productName) {
    	By removeBtn = By.xpath("//td[contains(normalize-space(.), '"+ productName +"')]/following-sibling::td/a[contains(text(), 'Remove')]");
        elementActionUtils.clickElement(removeBtn);
    }

    public void verifyProductPrice(String productName, String expectedPrice) {
    	By totalPrice = By.xpath("//td[contains(normalize-space(.), '"+ productName +"')]/following-sibling::td[text()='"+ expectedPrice +"']");
    	elementActionUtils.getTextAndCompare(totalPrice, expectedPrice);
    }
    
    public void clickShoppingCart() {
    	elementActionUtils.clickElement(SHOPPING_CART_BTN);
    }

    public void verifyProductLineTotal(String productName, String expectedTotal) {
    }
    
    public void clickProceedToCheckout() {
    	elementActionUtils.clickElement(PROCEED_TO_CHECKOUT_BTN);
    }


    
    
}