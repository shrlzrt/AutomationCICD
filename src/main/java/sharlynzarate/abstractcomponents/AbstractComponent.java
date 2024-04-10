package sharlynzarate.abstractcomponents;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sharlynzarate.pageobjects.CartPage;
import sharlynzarate.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']") 
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']") 
	WebElement ordersHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions .visibilityOfElementLocated(By.cssSelector(".mb-3")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
//		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}	
	public CartPage goToCartPage() { /* This is reusable because cart icon is in every page. */
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrdersPage goToOrdersPage() { /* This is reusable because "ORDERS" is in every page. */
		ordersHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
