package com.test.demo_petstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.demo_petstore.utils.ElementActionUtils;

public class BillingAddressPage {

    private WebDriver driver;
    private ElementActionUtils elementActionUtils;

    private By CARDTYPE_DROPDOWN = By.xpath("//select[@name='order.cardType']");
    private By CARDNUMBER_TXT = By.xpath("//input[@name='order.creditCard']");
    private By EXPIRYDATE_TXT = By.xpath("//input[@name='order.expiryDate']");
    private By SHIPPING_DIFFERENT_ADDRESS_CHK = By.xpath("//input[@name='shippingAddressRequired']");
    private By CONTINUE_BTN = By.xpath("//input[@value='Continue']");
    private By CONFIRM_BTN = By.xpath("//input[@value='Confirm']");
    private By CONFIRM_ORDER_BTN = By.xpath("//a[text()='Confirm']");

    public BillingAddressPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void enterCardType(String value) {
        elementActionUtils.changeDropdownValue(CARDTYPE_DROPDOWN, value);
    }

    public void enterCardNumber(String value) {
        elementActionUtils.inputElement(CARDNUMBER_TXT, value);
    }

    public void enterExpiryDate(String value) {
        elementActionUtils.inputElement(EXPIRYDATE_TXT, value);
    }

    public void enterValue(String order, String field, String value) {
    	Boolean result = elementActionUtils.assertIfContainsName(field);
    	if (result) {
    		elementActionUtils.inputElement(By.xpath("//input[@name='order."+ order+ "To" + field +"']"), value);
    	}else {
    		elementActionUtils.inputElement(By.xpath("//input[@name='order."+ order + field +"']"), value);			
		}
	}

    public void removeValue(String order, String field) {
    	Boolean result = elementActionUtils.assertIfContainsName(field);
    	if (result) {
    		elementActionUtils.removeInput(By.xpath("//input[@name='order."+ order+ "To" + field +"']"));
    	}else {
    		elementActionUtils.removeInput(By.xpath("//input[@name='order."+ order + field +"']"));			
		}
	}
    
    public void selectDifferentShippingAddress() {
        elementActionUtils.clickElement(SHIPPING_DIFFERENT_ADDRESS_CHK);
    }
    
    public void clickContinue() {
    	elementActionUtils.clickElement(CONTINUE_BTN);
    }
    
    public void clickConfirm() {
    	elementActionUtils.clickElement(CONFIRM_BTN);
    }
    
    public void clickConfirmOrder() {
    	elementActionUtils.clickElement(CONFIRM_ORDER_BTN);
    }
    
    public void removeCardNumber() {
        elementActionUtils.removeInput(CARDNUMBER_TXT);
    }

    public void removeExpiryDate() {
        elementActionUtils.removeInput(EXPIRYDATE_TXT);
    }
}
