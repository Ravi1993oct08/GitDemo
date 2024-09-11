package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submitButton;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElement(errorMessage);
		return errorMessage.getText();
	}
	
	public ProductCataloguePage loginApplication(String email,String passcode) {
		username.sendKeys(email);
		password.sendKeys(passcode);
		submitButton.submit();
		ProductCataloguePage pcp=new ProductCataloguePage(driver);
		return pcp;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
