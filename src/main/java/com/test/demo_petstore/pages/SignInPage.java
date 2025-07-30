package com.test.demo_petstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.demo_petstore.utils.ElementActionUtils;

public class SignInPage {

private WebDriver driver;
    private ElementActionUtils elementAction;

    private By USERNAME_TXT = By.name("username");
    private By PASSWORD_TXT = By.name("password");
    private By SIGNIN_BTN = By.name("signon");
    private By SIGNIN_PG_TTL = By.xpath("//h2[text()='Welcome to JPetStore 6']");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.elementAction = new ElementActionUtils(driver);
    }

    public void verifySignInPageIsDisplayed() {
        elementAction.verifyDisplayed(SIGNIN_PG_TTL);
    }

    public void enterUsername(String username) {
        elementAction.inputElement(USERNAME_TXT, username);
    }

    public void enterPassword(String password) {
            try {
                driver.findElement(PASSWORD_TXT).clear(); 
                elementAction.inputElement(PASSWORD_TXT, password);
            } catch (Exception e) {
                System.out.println("Failed to clear and enter password.");
                throw e;
            }
    }

    public void clickSignInButton() {
        elementAction.clickElement(SIGNIN_BTN);
    }

    public void loginToApp(String username, String password) {
        verifySignInPageIsDisplayed();
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
    }
    
}