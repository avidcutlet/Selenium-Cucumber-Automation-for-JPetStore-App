@Checkout
Feature: User Checkout Functionality

  Scenario: Verify Checkout Functionality via UI
    Given User is on the JPetStore landing page
    When User clicks on Enter the Store
    Then Verify user is redirected to the home page
    When User clicks Sign In link
    And User logs in the page
      | username | password    |
      | tester   | testerqt123 |
    Then Verify user is logged in successfully
    And User performs the following cart actions
      | action | productID | itemID | animalType | productName     | quantity |
      | add    | FI-SW-01  | EST-1  | fish       | Large Angelfish |        2 |
      | add    | FI-SW-01  | EST-2  | fish       | Small Angelfish |        2 |
      | remove | FI-SW-01  | EST-2  | fish       | Small Angelfish |        0 |
    Then Verify that total price matched
      | productName     | price  |
      | Large Angelfish | $33.00 |
    And User click proceed to checkout
    And User enter billing address
      | order | cardType   | cardNumber         | expiryDate | FirstName     | LastName      | Address1   | Address2    | City    | State | Zip   | Country     |
      | bill  | MasterCard | 999 9999 9999 9999 | 12/03      | AnotherFName1 | AnotherLName2 | 2 Main St. | Apartment B | Anytown | KS    | 12345 | Philippines |
    And User check ship to different address and click continue
    And User enter shipping address and click continue
      | order | FirstName     | LastName      | Address1   | Address2    | City    | State | Zip    | Country     |
      | ship  | AnotherFName2 | AnotherLName3 | 3 Main St. | Apartment A | Anytown | KS    | 123654 | Philippines |
    Then Verify order
      | Payment Details | Billing Address | Shipping Address | Card Type  | Card Number        | Expiry Date | First name    | Last name     | Address 1  | Address 2   | City    | State | Zip   | Country     | First name 2  | Last name 2   | Address 3  | Address 4   | City 2  | State 2 | Zip 2  | Country 2   | Courier | itemID | productName     | quantity | price  | totalCost |
      | Payment Details | Billing Address | Shipping Address | MasterCard | 999 9999 9999 9999 | 12/03       | AnotherFName1 | AnotherLName2 | 2 Main St. | Apartment B | Anytown | KS    | 12345 | Philippines | AnotherFName2 | AnotherLName3 | 3 Main St. | Apartment A | Anytown | KS      | 123654 | Philippines | UPS     | EST-1  | Large Angelfish |        2 | $16.50 | $33.00    |
