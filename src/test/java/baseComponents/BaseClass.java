package baseComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjectClasses.LandingPage;

public class BaseClass {

	public WebDriver driver;
	public LandingPage Lp;
	
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);

		String browser = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		Lp=new LandingPage(driver);
		Lp.goTo();
		return Lp;
	}
	
	//Reading data from json and converting it to HashMap
	public List<HashMap<String,String>> getJsonData(String filePath) throws IOException{
		String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
	}
	
	//screenshot code
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		 File reportsDir = new File(System.getProperty("user.dir") + "//reports");
	        if (!reportsDir.exists()) {
	            reportsDir.mkdirs();
	        }

	        FileUtils.copyFile(source, destination);
	        return destination.getAbsolutePath();
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		 driver.quit();
	}
	
}
