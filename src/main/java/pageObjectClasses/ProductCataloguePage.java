package pageObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent{
	
	WebDriver driver;
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@aria-label='Login Successfully']")
	WebElement loginMessage;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(xpath="//*[@aria-label='Login Successfully']")
	WebElement LoginMessage;
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> CardBodies;
	
	@FindBy(xpath="//div[@aria-label='Product Added To Cart']")
	WebElement productMessage;
	
	public void findProducts(String product) {
		waitForInvisibility(loginMessage);
		for (int i = 0; i < CardBodies.size(); i++) {
			String name = CardBodies.get(i).findElement(By.xpath("//h5//b")).getText();

			if (name.equalsIgnoreCase(product)) {
				CardBodies.get(i).findElement(By.xpath("//*[@class='btn w-10 rounded']")).click();
				break;
			}
		}
		waitForWebElement(productMessage);
	}
	
	public CartPage goToCartPage() {
		cartIcon.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}
}

