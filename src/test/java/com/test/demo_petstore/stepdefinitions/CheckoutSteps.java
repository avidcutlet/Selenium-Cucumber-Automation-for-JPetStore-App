package com.test.demo_petstore.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.test.demo_petstore.hooks.Hooks;
import com.test.demo_petstore.pages.BillingAddressPage;
import com.test.demo_petstore.pages.HomePage;
import com.test.demo_petstore.pages.OrderConfirmationPage;
import com.test.demo_petstore.pages.OrderPage;
import com.test.demo_petstore.pages.ShippingAddressPage;
import com.test.demo_petstore.pages.ShoppingCartPage;
import com.test.demo_petstore.pages.SignInPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class CheckoutSteps {
	 
	WebDriver driver = Hooks.driver;
	
	HomePage homePage;
	BillingAddressPage billingAddressPage;
	OrderConfirmationPage orderConfirmationPage;
	OrderPage orderPage;
	ShippingAddressPage shippingAddressPage;
	ShoppingCartPage shoppingCartPage;
	SignInPage signInPage;
	
	public CheckoutSteps() {
		this.homePage = new HomePage(driver);
		this.signInPage = new SignInPage(driver);
		this.shoppingCartPage = new ShoppingCartPage(driver);
		this.billingAddressPage = new BillingAddressPage(driver);
		this.shippingAddressPage = new ShippingAddressPage(driver);
		this.orderConfirmationPage = new OrderConfirmationPage(driver);
		this.orderPage = new OrderPage(driver);
	}
	
    @Given("User is on the JPetStore landing page")
    public void user_is_on_the_j_pet_store_landing_page() {
        homePage.verifyLandingPageMessage();
    }

    @When("User clicks on Enter the Store")
    public void user_clicks_on_enter_the_store() {
        homePage.clickEnterStore();
    }

    @Then("Verify user is redirected to the home page")
    public void verify_user_redirected_to_the_home_page() {
        homePage.verifyHomePage();
    }

    @When("User clicks Sign In link")
    public void user_clicks_sign_in_link() {
        homePage.clickSignIn();
    }
    
    @And("User logs in the page")
    public void user_logs_in_the_page(DataTable table) {
        Map<String, String> data = table.asMaps(String.class, String.class).get(0);
        String username = data.get("username").trim();
        String password = data.get("password").trim();

        signInPage.enterUsername(username);
        signInPage.enterPassword(password);
        signInPage.clickSignInButton();
    }

    @Then("Verify user is logged in successfully")
    public void user_logged_in_successfully() {
        homePage.verifyUserIsLoggedIn();
    }

    @And("User performs the following cart actions")
    public void user_performs_the_following_cart_actions(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String action = row.get("action").trim();
            String productID = row.get("productID").trim();
            String itemID = row.get("itemID").trim();
            String animalType = row.get("animalType").trim();
            String productName = row.get("productName").trim();
            String quantity = row.get("quantity").trim();

            // Perform cart actions via page object
            if (action.equals("add")) {
	            homePage.navigateToCategory(animalType);
	            homePage.selectProductBreed(productID);
	            homePage.selectProductItem(itemID);
	            homePage.addToCartByItemId();
	            shoppingCartPage.updateQuantity(productName, itemID, quantity);
	            homePage.clickReturnToMainMenu();
            } else {
            	shoppingCartPage.clickShoppingCart();
            	shoppingCartPage.removeProduct(productName);
            }
        }
    }

    @Then("Verify that total price matched")
    public void verify_that_total_price_matched(DataTable table) {
        Map<String, String> data = table.asMaps(String.class, String.class).get(0);
        String productName = data.get("productName").trim();
        String price = data.get("price").trim();

        // Verify price via page object
        shoppingCartPage.verifyProductPrice(productName, price);
        
    }

    @And("User click proceed to checkout")
    public void user_click_proceed_to_checkout() {

        shoppingCartPage.clickProceedToCheckout();
    }

    @And("User enter billing address")
    public void user_enter_billing_address(DataTable table) {
        List<String> headers = table.row(0);
        String headerFirstName = headers.get(4);
        String headerLastName = headers.get(5);
        String headerAddress1 = headers.get(6);
        String headerAddress2 = headers.get(7);
        String headerCity = headers.get(8);
        String headerState = headers.get(9);
        String headerZip = headers.get(10);
        String headerCountry = headers.get(11);
        
        Map<String, String> billing  = table.asMaps(String.class, String.class).get(0);
        String order = billing.get("order");
        String cardType = billing.get("cardType").trim();
        String cardNumber = billing.get("cardNumber").trim();
        String expiryDate = billing.get("expiryDate").trim();
        String firstName = billing.get("FirstName").trim();
        String lastName = billing.get("LastName").trim();
        String address1 = billing.get("Address1").trim();
        String address2 = billing.get("Address2").trim();
        String city = billing.get("City").trim();
        String state = billing.get("State").trim();
        String zip = billing.get("Zip").trim();
        String country = billing.get("Country").trim();
        
        // Remove data to billing page
        billingAddressPage.removeCardNumber();
        billingAddressPage.removeExpiryDate();
        billingAddressPage.removeValue(order, headerFirstName);
        billingAddressPage.removeValue(order, headerLastName);
        billingAddressPage.removeValue(order, headerAddress1);
        billingAddressPage.removeValue(order, headerAddress2);
        billingAddressPage.removeValue(order, headerCity);
        billingAddressPage.removeValue(order, headerState);
        billingAddressPage.removeValue(order, headerZip);
        billingAddressPage.removeValue(order, headerCountry);

        // Send data to billing page
        billingAddressPage.enterCardType(cardType);
        billingAddressPage.enterCardNumber(cardNumber);
        billingAddressPage.enterExpiryDate(expiryDate);
        billingAddressPage.enterValue(order, headerFirstName, firstName);
        billingAddressPage.enterValue(order, headerLastName, lastName);
        billingAddressPage.enterValue(order, headerAddress1, address1);
        billingAddressPage.enterValue(order, headerAddress2, address2);
        billingAddressPage.enterValue(order, headerCity, city);
        billingAddressPage.enterValue(order, headerState, state);
        billingAddressPage.enterValue(order, headerZip, zip);
        billingAddressPage.enterValue(order, headerCountry, country);
    }

    @And("User check ship to different address and click continue")
    public void user_check_ship_to_different_address_and_click_continue() {
        // Handle checkbox + continue
    	billingAddressPage.selectDifferentShippingAddress();
    	billingAddressPage.clickContinue();
    }

    @And("User enter shipping address and click continue")
    public void user_enter_shipping_address_and_click_continue(DataTable table) {
        List<String> headers = table.row(0);
        String headerFirstName = headers.get(1);
        String headerLastName = headers.get(2);
        String headerAddress1 = headers.get(3);
        String headerAddress2 = headers.get(4);
        String headerCity = headers.get(5);
        String headerState = headers.get(6);
        String headerZip = headers.get(7);
        String headerCountry = headers.get(8);
        
        Map<String, String> shipping  = table.asMaps(String.class, String.class).get(0);
        String order = shipping.get("order");
        String firstName = shipping.get("FirstName").trim();
        String lastName = shipping.get("LastName").trim();
        String address1 = shipping.get("Address1").trim();
        String address2 = shipping.get("Address2").trim();
        String city = shipping.get("City").trim();
        String state = shipping.get("State").trim();
        String zip = shipping.get("Zip").trim();
        String country = shipping.get("Country").trim();
        
        // Remove data to billing page
        billingAddressPage.removeValue(order, headerFirstName);
        billingAddressPage.removeValue(order, headerLastName);
        billingAddressPage.removeValue(order, headerAddress1);
        billingAddressPage.removeValue(order, headerAddress2);
        billingAddressPage.removeValue(order, headerCity);
        billingAddressPage.removeValue(order, headerState);
        billingAddressPage.removeValue(order, headerZip);
        billingAddressPage.removeValue(order, headerCountry);

        // Send data to shipping page
        billingAddressPage.enterValue(order, headerFirstName, firstName);
        billingAddressPage.enterValue(order, headerLastName, lastName);
        billingAddressPage.enterValue(order, headerAddress1, address1);
        billingAddressPage.enterValue(order, headerAddress2, address2);
        billingAddressPage.enterValue(order, headerCity, city);
        billingAddressPage.enterValue(order, headerState, state);
        billingAddressPage.enterValue(order, headerZip, zip);
        billingAddressPage.enterValue(order, headerCountry, country);
        
        billingAddressPage.clickContinue();
        billingAddressPage.clickConfirmOrder();
    }
    
    @Then("Verify order")
    public void verify_order(DataTable table) {
        List<String> headers = table.row(0);
        String headerPaymentDetails = headers.get(0);
        String headerBillingDetails = headers.get(1);
        String headerShippingDetails = headers.get(2);
        String headerCardType = headers.get(3);
        String headerCardNumber = headers.get(4);
        String headerExpiryDate = headers.get(5);
        String headerFirstName = headers.get(6);
        String headerLastName = headers.get(7);
        String headerAddress1 = headers.get(8);
        String headerAddress2 = headers.get(9);
        String headerCity = headers.get(10);
        String headerState = headers.get(11);
        String headerZip = headers.get(12);
        String headerCountry = headers.get(13);
        String headerCourier = headers.get(22);
        
        Map<String, String> values  = table.asMaps(String.class, String.class).get(0);
        String cardType = values.get("Card Type").trim();
        String cardNumber = values.get("Card Number").trim();
        String expiryDate = values.get("Expiry Date");
        
        String billFirstName = values.get("First name").trim();
        String billLastName = values.get("Last name").trim();
        String billAddress1 = values.get("Address 1").trim();
        String billAddress2 = values.get("Address 2").trim();
        String billCity = values.get("City").trim();
        String billState = values.get("State").trim();
        String billZip = values.get("Zip").trim();
        String billCountry = values.get("Country").trim();
        
        String shipFirstName = values.get("First name 2").trim();
        String shipLastName = values.get("Last name 2").trim();
        String shipAddress1 = values.get("Address 3").trim();
        String shipAddress2 = values.get("Address 4").trim();
        String shipCity = values.get("City 2").trim();
        String shipState = values.get("State 2").trim();
        String shipZip = values.get("Zip 2").trim();
        String shipCountry = values.get("Country 2").trim();
        String shipCourier = values.get("Courier").trim();

        String itemID = values.get("itemID").trim();
        String productName = values.get("productName").trim();
        String quantity = values.get("quantity").trim();
        String price = values.get("price").trim();
        String totalCost = values.get("totalCost").trim();
        
        orderPage.verifyDetails(headerPaymentDetails, headerCardType, cardType, cardType);
        orderPage.verifyDetails(headerPaymentDetails, headerCardNumber, cardNumber, cardNumber);
        orderPage.verifyDetails(headerPaymentDetails, headerExpiryDate, expiryDate, expiryDate);
        
        orderPage.verifyDetails(headerBillingDetails, headerFirstName, billFirstName, billFirstName);
        orderPage.verifyDetails(headerBillingDetails, headerLastName, billLastName, billLastName);
        orderPage.verifyDetails(headerBillingDetails, headerAddress1, billAddress1, billAddress1);
        orderPage.verifyDetails(headerBillingDetails, headerAddress2, billAddress2, billAddress2);
        orderPage.verifyDetails(headerBillingDetails, headerCity, billCity, billCity);
        orderPage.verifyDetails(headerBillingDetails, headerState, billState, billState);
        orderPage.verifyDetails(headerBillingDetails, headerZip, billZip, billZip);
        orderPage.verifyDetails(headerBillingDetails, headerCountry, billCountry, billCountry);

        orderPage.verifyDetails(headerShippingDetails, headerFirstName, shipFirstName, shipFirstName);
        orderPage.verifyDetails(headerShippingDetails, headerLastName, shipLastName, shipLastName);
        orderPage.verifyDetails(headerShippingDetails, headerAddress1, shipAddress1, shipAddress1);
        orderPage.verifyDetails(headerShippingDetails, headerAddress2, shipAddress2, shipAddress2);
        orderPage.verifyDetails(headerShippingDetails, headerCity, shipCity, shipCity);
        orderPage.verifyDetails(headerShippingDetails, headerState, shipState, shipState);
        orderPage.verifyDetails(headerShippingDetails, headerZip, shipZip, shipZip);
        orderPage.verifyDetails(headerShippingDetails, headerCountry, shipCountry, shipCountry);
        orderPage.verifyDetails(headerShippingDetails, headerCourier, shipCourier, shipCourier);
     
        orderPage.verifyItemID(itemID, itemID);
        orderPage.verifyProductName(productName, productName);
        orderPage.verifyQuantity(quantity, quantity);
        orderPage.verifyPrice(price, price);
        orderPage.verifyTotalCost(totalCost, totalCost);
    }
}
