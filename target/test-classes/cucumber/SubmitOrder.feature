@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | 		name  		| 	password 		| 	productName 	|
      | spz@gmail.com |		Udemytest!1	| ZARA COAT 3 		|
      | zps@gmail.com | 	Udemytest!2 | ADIDAS ORIGINAL |
 			| szz@gmail.com | 	Udemytest!3 | IPHONE 13 PRO   |