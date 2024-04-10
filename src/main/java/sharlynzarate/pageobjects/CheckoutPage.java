package sharlynzarate.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sharlynzarate.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="*[placeholder='Select Country']") 
	private WebElement country;

	private By countrySearchResult = By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]") 
	private WebElement selectCountry2nd;
	
	@FindBy(css=".action__submit") 
	private WebElement BTNPlaceOrder;
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("*[placeholder='Select Country']")), "india").build().perform();
		a.sendKeys(country, countryName).build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		waitForElementToAppear(countrySearchResult);
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		selectCountry2nd.click();
	}
	public ConfirmationPage submitOrder() {
//		driver.findElement(By.cssSelector(".action__submit")).click();
		BTNPlaceOrder.click();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;
	}	
}
