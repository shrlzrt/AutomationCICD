package sharlynzarate.tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sharlynzarate.pageobjects.CartPage;
import sharlynzarate.pageobjects.CheckoutPage;
import sharlynzarate.pageobjects.ConfirmationPage;
import sharlynzarate.pageobjects.LandingPage;
import sharlynzarate.pageobjects.OrdersPage;
import sharlynzarate.pageobjects.ProductCatalog;
import sharlynzarate.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase", "Smoke" }) // TC001
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalog prodCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = prodCatalog.getProductList();
		prodCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = prodCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" }) // TC002
	public void orderHistoryTest() {
		ProductCatalog prodCatalog = landingPage.loginApplication("spz@gmail.com", "Udemytest!1");
		OrdersPage ordersPage = prodCatalog.goToOrdersPage();

		Boolean match = ordersPage.verifyOrdersDisplay(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJSONDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\sharlynzarate\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
//		return new Object[][] {{"spz@gmail.com","Udemytest!1","ZARA COAT 3"},{"zps@gmail.com","Udemytest!2","ADIDAS ORIGINAL"},{"szz@gmail.com","Udemytest!3","IPHONE 13 PRO"}};	
	}
}






/*
 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
 * "spz@gmail.com"); map.put("password", "Udemytest!1"); map.put("productName",
 * "ZARA COAT 3");
 * 
 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
 * "zps@gmail.com"); map1.put("password", "Udemytest!2");
 * map1.put("productName", "ADIDAS ORIGINAL");
 * 
 * HashMap<String,String> map2 = new HashMap<String,String>(); map2.put("email",
 * "szz@gmail.com"); map2.put("password", "Udemytest!3");
 * map2.put("productName", "IPHONE 13 PRO");
 */