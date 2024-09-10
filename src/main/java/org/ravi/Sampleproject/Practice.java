package org.ravi.Sampleproject;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Practice {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		
		String Title=  driver.getTitle();
		System.out.println(Title);
		
		String currentUrl=driver.getCurrentUrl();
		System.out.println(currentUrl);
		
		WebElement ddown= driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		
		Select dropdown=new Select(ddown);
		Thread.sleep(2000);
		dropdown.selectByIndex(0);
		Thread.sleep(2000);
		dropdown.selectByValue("AED");
		Thread.sleep(2000);
		dropdown.selectByVisibleText("USD");
		
		driver.findElement(By.id("divpaxinfo")).click();
		WebElement adult=driver.findElement(By.id("hrefIncAdt"));
		Actions act= new Actions(driver);
		//act.moveToElement(adult).click();
		
		wait.until(ExpectedConditions.visibilityOf(adult)).click();
		
		driver.findElement(By.id("btnclosepaxoption")).click();
		
		WebElement autosuggestion= driver.findElement(By.id("autosuggest"));
		autosuggestion.sendKeys("Ind");
		Thread.sleep(2000);
		List<WebElement> countryNames= driver.findElements(By.className("ui-corner-all"));
		//wait.until(ExpectedConditions.visibilityOfAllElements(countryNames));
		for(WebElement country:countryNames) {
			if(country.getText().equalsIgnoreCase("India")) {
				country.click();
			}
		}
		
		System.out.println(driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).isSelected());
		System.out.println(driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).isDisplayed());
		System.out.println(driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).isEnabled());
	}

}
