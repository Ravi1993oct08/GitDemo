package raviacademy;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjectClasses.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String productList = "ZARA COAT 3";

		LandingPage Lp = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("raviaarya112@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vivan@123");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//*[@aria-label='Login Successfully']"))));

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='card-body']"));

		for (int i = 0; i < list.size(); i++) {
			String product = list.get(i).findElement(By.xpath("//h5//b")).getText();

			if (product.equalsIgnoreCase(productList)) {
				list.get(i).findElement(By.xpath("//*[@class='btn w-10 rounded']")).click();
				break;
			}
		}
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> ordersList = driver.findElements(By.xpath("//ul[contains(@class,'cartWrap')]"));
		for (WebElement order : ordersList) {
			if (order.findElement(By.xpath("//li//div//div//h3")).getText().equalsIgnoreCase(productList)) {
				order.findElement(By.xpath("//li//div//div//button[contains(@class,'btn-primary')]")).click();

			}
		}
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[contains(@class,'ta-results')]//span"))));
		List<WebElement> countryNames = driver.findElements(By.xpath("//*[contains(@class,'ta-results')]//span"));

		for (WebElement cout : countryNames) {
			String name = cout.getText().trim();
			if (name.equalsIgnoreCase("India")) {
				// JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", cout);

				break;
			}
		}
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[contains(text(),'Place Order ')]")));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//*[@aria-label='Order Placed Successfully']"))));
		String confirmationmessage = driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
		Assert.assertTrue("Thankyou for the order.".equalsIgnoreCase(confirmationmessage));
		driver.quit();
	}

}
