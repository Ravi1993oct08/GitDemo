package org.ravi.Sampleproject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxClass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/documents-request");
		driver.manage().window().maximize();
		
		WebElement element=driver.findElement(By.xpath("//*[@class='main-footer']"));
		List<WebElement> links=element.findElements(By.tagName("a"));
		for(WebElement link:links) {
			link.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		}
	}

}
