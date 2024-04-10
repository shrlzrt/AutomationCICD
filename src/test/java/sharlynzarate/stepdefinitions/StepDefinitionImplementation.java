package sharlynzarate.stepdefinitions;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sharlynzarate.pageobjects.CartPage;
import sharlynzarate.pageobjects.CheckoutPage;
import sharlynzarate.pageobjects.ConfirmationPage;
import sharlynzarate.pageobjects.LandingPage;
import sharlynzarate.pageobjects.ProductCatalog;
import sharlynzarate.testcomponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog prodCatalog;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
/*	Given Logged in with username <name> and password <password> */
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) {
//		ProductCatalog prodCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		prodCatalog = landingPage.loginApplication(username,password);
	}	
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = prodCatalog.getProductList();
		prodCatalog.addProductToCart(productName);
	}	
	
/*	 And Checkout <productName> and submit the order */
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String productName) {
		CartPage cartPage = prodCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
//		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		confirmationPage = checkoutPage.submitOrder();
	}	
	
/*	Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage */
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));		
		driver.close();
	}
	
/*	 Then "Incorrect email password." message is displayed */		
	 @Then("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String errorMsg) throws Throwable {
	   
	    	Assert.assertEquals(errorMsg, landingPage.getErrorMessage());
	    	driver.close();
	    }

	
}



