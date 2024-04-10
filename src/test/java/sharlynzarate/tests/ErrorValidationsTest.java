package sharlynzarate.tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sun.net.httpserver.Authenticator.Retry;
import sharlynzarate.pageobjects.CartPage;
import sharlynzarate.pageobjects.CheckoutPage;
import sharlynzarate.pageobjects.ConfirmationPage;
import sharlynzarate.pageobjects.ProductCatalog;
import sharlynzarate.testcomponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer=sharlynzarate.testcomponents.Retry.class) // TC003 
	public void loginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("spz@gmail.com", "Udemytest"); // -> provided wrong pw
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}

	@Test 
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog prodCatalog = landingPage.loginApplication("spz@gmail.com", "Udemytest!1");
		prodCatalog.addProductToCart(productName);
		CartPage cartPage = prodCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match); //Execution should pass since it is expected to have false match

	}
}
