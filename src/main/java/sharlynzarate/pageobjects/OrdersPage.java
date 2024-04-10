package sharlynzarate.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sharlynzarate.abstractcomponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)") 
	List<WebElement> ordersList;
	
	public Boolean verifyOrdersDisplay(String productName) {
		Boolean match = ordersList.stream().anyMatch(cProduct->cProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
}
