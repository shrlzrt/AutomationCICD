package sharlynzarate.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sharlynzarate.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	//create a constructor
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail") 
	WebElement userEmail;
	
	@FindBy(id="userPassword") 
	WebElement userPw;
	
	@FindBy(id="login") 
	WebElement BTNsubmit;
	
	@FindBy(css="#toast-container") 
	WebElement errorMessage;
	
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPw.sendKeys(password);
		BTNsubmit.click();
		ProductCatalog prodCatalog = new ProductCatalog(driver);
		return prodCatalog;
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();			
	}	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");	
	}	
}

