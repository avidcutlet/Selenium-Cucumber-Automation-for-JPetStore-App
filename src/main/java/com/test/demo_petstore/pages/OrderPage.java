package com.test.demo_petstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.demo_petstore.utils.ElementActionUtils;

public class OrderPage {

    private WebDriver driver;
    
    private ElementActionUtils elementActionUtils;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        
        this.elementActionUtils = new ElementActionUtils(driver);
    }
    
    public void verifyDetails(String category, String field, String value, String expectedText) {
    	By fieldLocator= By.xpath(""
    			+ "//tr[th[text()='"+ category +"']]"
    			+ "/following-sibling::tr/child::"
    			+ "td[contains(text(), '"+ field +"')]"
    			+ "/following-sibling::td[contains(text(), '"+ value +"')]");
    	elementActionUtils.getTextAndCompare(fieldLocator, expectedText);
    }

    public void verifyItemID(String itemID, String expectedItemID) {
    	By itemIDLocator = By.xpath("//a[text()='"+ itemID +"']");
    	
    	elementActionUtils.getTextAndCompare(itemIDLocator, expectedItemID);
    }
    
    public void verifyProductName(String productName, String expectedProductName) {
    	By productNameLocator = By.xpath("//td[normalize-space(.)='"+ productName +"']");
    	
    	elementActionUtils.getTextAndCompare(productNameLocator, expectedProductName);
    }
    
    public void verifyQuantity(String quantity, String expectedQuantity) {
    	By quantityLocator = By.xpath("//td[text()='"+ quantity +"']");
    	
    	elementActionUtils.getTextAndCompare(quantityLocator, expectedQuantity);
    }
    public void verifyPrice(String price, String expectedPrice) {
    	By priceLocator = By.xpath("//td[text()='"+ price +"']");
    	
    	elementActionUtils.getTextAndCompare(priceLocator, expectedPrice);
    }
    
    public void verifyTotalCost(String totalCost, String expectedTotalCost) {
    	By totalCostLocator = By.xpath("//td[text()='"+ totalCost +"']");
    	
    	elementActionUtils.getTextAndCompare(totalCostLocator, expectedTotalCost);
    	
    }
}
