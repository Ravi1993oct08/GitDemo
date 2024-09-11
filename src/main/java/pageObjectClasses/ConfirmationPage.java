package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='hero-primary']")
	WebElement title;
	
	public Boolean getMessage(String message) {
		String title1=title.getText();
				
	 return title1.equalsIgnoreCase(message);
	}
	
}
/*String confirmationmessage = driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
		Assert.assertTrue("Thankyou for the order.".equalsIgnoreCase(confirmationmessage));
		*/
