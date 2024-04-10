package sharlynzarate.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sharlynzarate.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3") 
	List<WebElement> cartProducts;
	
//	driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button") 
	WebElement BTNcheckout;
	
	public Boolean verifyProductDisplay(String productName) {
//		Boolean match = cartProducts.stream().anyMatch(cProduct->cProduct.getText().equalsIgnoreCase(productName));
		Boolean match = cartProducts.stream().anyMatch(cProduct->cProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckout() {
//		driver.findElement(By.cssSelector(".totalRow button")).click();
		BTNcheckout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
