package sharlynzarate.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sharlynzarate.abstractcomponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{

	WebDriver driver;
	//create a constructor
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3") 
	List<WebElement> products;
	
	@FindBy(css=".ng-animating") 
	WebElement spinner;
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
//		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		prod.findElement(addToCart).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		waitForElementToAppear(toastMessage);
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		waitForElementToDisappear(spinner);
	}
}

