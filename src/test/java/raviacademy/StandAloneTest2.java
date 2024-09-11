package raviacademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseComponents.BaseClass;
import baseComponents.Retry;
import pageObjectClasses.CartPage;
import pageObjectClasses.CheckOutPage;
import pageObjectClasses.ConfirmationPage;
import pageObjectClasses.ProductCataloguePage;

public class StandAloneTest2 extends BaseClass {

	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException {

	
		
		String message = "Thankyou for the order.";

		ProductCataloguePage pcp = Lp.loginApplication(input.get("email"), input.get("password"));
		pcp.findProducts(input.get("productList"));

		CartPage cp = pcp.goToCartPage();

		CheckOutPage cop = cp.checkTheProductsAndGoTo(input.get("productList"));
		cop.selectCountry(input.get("countryName"));

		ConfirmationPage confirmationpage = cop.clickOnPlaceOrder();
		Boolean match = confirmationpage.getMessage(message);

		Assert.assertTrue(match);

	}
	
	//This will fee the data to Submit order method above
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\purchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
