package pageObjectClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(xpath="//*[contains(@class,'ta-results')]//span")
	WebElement countryNames;
	
	@FindBy(xpath="//*[contains(@class,'ta-results')]//span")
	List<WebElement> countryNamesList;
	
	@FindBy(xpath="//*[contains(text(),'Place Order ')]")
	WebElement placeOrderButton;
	
	@FindBy(xpath="//*[@aria-label='Order Placed Successfully']")
	WebElement disappearingMessage;
	
	public void selectCountry(String country) {
		countryField.sendKeys(country);
		waitForWebElement(countryNames);
		for (WebElement cout : countryNamesList) {
			String name = cout.getText().trim();
			if (name.equalsIgnoreCase(country)) {
				
				clickWithJS(cout);

				break;
			}
		}
	}
	
	public ConfirmationPage clickOnPlaceOrder() {
		clickWithJS(placeOrderButton);
		waitForInvisibility(disappearingMessage);
		ConfirmationPage confirmationpage=new ConfirmationPage(driver);
		return confirmationpage;
	}
}
/*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[contains(@class,'ta-results')]//span"))));
		List<WebElement> countryNames = driver.findElements(By.xpath("//*[contains(@class,'ta-results')]//span"));

		for (WebElement cout : countryNames) {
			String name = cout.getText().trim();
			if (name.equalsIgnoreCase("India")) {
				// JavascriptExecutor js=(JavascriptExecutor) driver;
				

				break;
			}
		}
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[contains(text(),'Place Order ')]")));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//*[@aria-label='Order Placed Successfully']"))));
				*/
