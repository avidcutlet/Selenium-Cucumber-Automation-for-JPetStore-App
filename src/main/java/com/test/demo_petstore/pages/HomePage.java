package com.test.demo_petstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.demo_petstore.utils.ElementActionUtils;

import java.util.HashMap;
import java.util.Map;

public class HomePage {

    private WebDriver driver;
    private ElementActionUtils elementActionUtils;

    // Locators
    private static final By SIGN_IN_LNK = By.xpath("//a[text()='Sign In']");
    private static final By SIGN_OUT_LNK = By.xpath("//a[text()='Sign Out']");
    private static final By MY_ACCOUNT_LNK = By.xpath("//a[text()='My Account']");
    private static final By RETURN_TO_MAIN_BTN = By.xpath("//a[text()='Return to Main Menu']");
    private static final By LANDING_MESSAGE_TXT = By.xpath("//h2[text()='Welcome to JPetStore 6']");
    private static final By ENTER_STORE_LNK = By.xpath("//a[text()='Enter the Store']");
    private static final By ADD_TO_CART_BTN = By.xpath("//a[text()='Add to Cart']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }
    
    public void verifyHomePage() {
    	elementActionUtils.verifyDisplayed(SIGN_IN_LNK);
    }

    // Landing page actions
    public void verifyLandingPageMessage() {
        elementActionUtils.verifyDisplayed(LANDING_MESSAGE_TXT);
    }

    public void clickReturnToMainMenu() {
    	elementActionUtils.clickElement(RETURN_TO_MAIN_BTN);
    }
    public void clickEnterStore() {
        elementActionUtils.clickElement(ENTER_STORE_LNK);
    }

    public void clickSignIn() {
        elementActionUtils.clickElement(SIGN_IN_LNK);
    }

    public void verifyUserIsLoggedIn() {
        elementActionUtils.verifyDisplayed(SIGN_OUT_LNK);
    }

    public void clickSignOut() {
        elementActionUtils.clickElement(SIGN_OUT_LNK);
    }

    public void goToMyAccount() {
        elementActionUtils.clickElement(MY_ACCOUNT_LNK);
    }

    public void navigateToCategory(String categoryName) {
        elementActionUtils.clickElement(By.xpath("//img[@src='../images/"+ categoryName +"_icon.gif']"));
    }

    public void selectProductBreed(String productId) {
        elementActionUtils.clickElement(By.xpath("//a[contains(@href, 'productId=" + productId + "')]"));
    }

    public void selectProductItem(String itemId) {
        elementActionUtils.clickElement(By.xpath("//a[contains(@href, 'itemId=" + itemId + "')]"));
    }
    
    public void addToCartByItemId() {
        elementActionUtils.clickElement(ADD_TO_CART_BTN);
    }

     
}
