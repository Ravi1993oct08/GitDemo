package org.ravi.Sampleproject;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MoreWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		driver.manage().window().maximize();
		
		String[] itemsNeeded= {"Potato","Beetroot","Brinjal","Raspberry","Cucumber"};
		
		List<WebElement> productList= driver.findElements(By.xpath("//*[@class='product-name']"));
		for(int i=0;i<productList.size();i++) {
			String productName=productList.get(i).getText();
			String name=productName.split("-")[0].trim();
			List<String> al=Arrays.asList(itemsNeeded);
			if(al.contains(name)){
				driver.findElements(By.xpath("//div[@class='product-action']//button[@type='button']")).get(i).click();
			}
		}
	}

}
