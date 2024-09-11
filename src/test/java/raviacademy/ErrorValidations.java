package raviacademy;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseComponents.BaseClass;
import baseComponents.Retry;
import pageObjectClasses.CartPage;
import pageObjectClasses.CheckOutPage;
import pageObjectClasses.ConfirmationPage;
import pageObjectClasses.ProductCataloguePage;

public class ErrorValidations extends BaseClass {

	@Test(groups= {"purchase"},retryAnalyzer=Retry.class)
	public void LoginVerification() throws IOException {
		
		Lp.loginApplication("raviaarya12@gmail.com", "Vivan@123");
		Assert.assertTrue(Lp.getErrorMessage().equalsIgnoreCase("Incorrect email or password."));
	}
	
	@Test
	public void productValidation() {
		String productList = "ZARA COAT 3";
		String countryName = "India";
		String message = "Thankyou for the order.";

		ProductCataloguePage pcp = Lp.loginApplication("anshika@gmail.com", "Iamking@000");
		pcp.findProducts(productList);

		CartPage cp = pcp.goToCartPage();

		CheckOutPage cop = cp.checkTheProductsAndGoTo(productList);
		cop.selectCountry(countryName);

		ConfirmationPage confirmationpage = cop.clickOnPlaceOrder();
		Boolean match = confirmationpage.getMessage(message);

		Assert.assertTrue(match);
	}

}
