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

public class Practice2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		
		String Title=  driver.getTitle();
		System.out.println(Title);
		
		String currentUrl=driver.getCurrentUrl();
		System.out.println(currentUrl);
		driver.findElement(By.xpath("//*[@onclick='alertbox()']")).click();
		System.out.print(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		
	
	}

}
