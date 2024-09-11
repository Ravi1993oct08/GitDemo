package pageObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[contains(@class,'cartWrap')]")
	List<WebElement> ordersList;
	
	
	
	public CheckOutPage checkTheProductsAndGoTo(String productList) {
		for (WebElement order : ordersList) {
			if (order.findElement(By.xpath("//li//div//div//h3")).getText().equalsIgnoreCase(productList)) {
				order.findElement(By.xpath("//li//div//div//button[contains(@class,'btn-primary')]")).click();

			}
		}
		CheckOutPage cop=new CheckOutPage(driver);
		return cop;
	}
	
}

