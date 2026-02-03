package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven")
	public void verify_loginDDT(String email, String password, String expectedResult) {
		logger.info("--------starting login test----");
		try {
	    HomePage hp = new HomePage(driver);
	    hp.clickMyAccount();
	    hp.Login_btn();

	    LoginPage lp = new LoginPage(driver);
	    lp.EnterMail(email);
	    lp.EnterPWD(password);
	    lp.ClickLogiN();

	    MyAccountpage macc = new MyAccountpage(driver);
	    boolean targetPage = macc.MyAcuntPage();

	    if (expectedResult.equalsIgnoreCase("Valid")) {
	        if (targetPage) {
	            macc.logoutAc();
	            Assert.assertTrue(true);
	        } else {
	            Assert.fail("Expected valid login, but account page not found");
	        }
	    }

	    if (expectedResult.equalsIgnoreCase("Invalid")) {
	        if (targetPage) {
	            macc.logoutAc();
	            Assert.fail("Expected login to fail, but account page was found");
	        } else {
	            Assert.assertTrue(true);
	        }
	    }
	}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		logger.info("--------finished test----");
		}
}
