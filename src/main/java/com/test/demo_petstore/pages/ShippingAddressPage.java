package com.test.demo_petstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.demo_petstore.utils.ElementActionUtils;

public class ShippingAddressPage {

    private WebDriver driver;
    private ElementActionUtils elementAction;

    public ShippingAddressPage(WebDriver driver) {
        this.driver = driver;
        this.elementAction = new ElementActionUtils(driver);
    }

    private By SHIPPING_FIRST_NAME = By.name("order.shipToFirstName");
    private By SHIPPING_LAST_NAME = By.name("order.shipToLastName");
    private By SHIPPING_ADDRESS_1 = By.name("order.shipAddress1");
    private By SHIPPING_ADDRESS_2 = By.name("order.shipAddress2");
    private By SHIPPING_CITY = By.name("order.shipCity");
    private By SHIPPING_STATE = By.name("order.shipState");
    private By SHIPPING_ZIP = By.name("order.shipZip");
    private By SHIPPING_COUNTRY = By.name("order.shipCountry");
    private By CONTINUE_BUTTON = By.name("newOrder");

    public void enterShippingInformation(
        String firstName,
        String lastName,
        String address1,
        String address2,
        String city,
        String state,
        String zip,
        String country
    ) {
        elementAction.inputElement(SHIPPING_FIRST_NAME, firstName);
        elementAction.inputElement(SHIPPING_LAST_NAME, lastName);
        elementAction.inputElement(SHIPPING_ADDRESS_1, address1);
        elementAction.inputElement(SHIPPING_ADDRESS_2, address2);
        elementAction.inputElement(SHIPPING_CITY, city);
        elementAction.inputElement(SHIPPING_STATE, state);
        elementAction.inputElement(SHIPPING_ZIP, zip);
        elementAction.inputElement(SHIPPING_COUNTRY, country);
    }

    public void clickContinue() {
        elementAction.clickElement(CONTINUE_BUTTON);
    }
}
