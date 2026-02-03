package testCases;

//import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"sanity","master"})
	public void Verify_Login()
	{
		logger.info("--------starting login test----");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.Login_btn();
	LoginPage lp=new LoginPage(driver);
	lp.EnterMail(p.getProperty("email"));
	lp.EnterPWD(p.getProperty("password"));
lp.ClickLogiN();
MyAccountpage mac=new MyAccountpage(driver);
boolean TARGETpage=mac.MyAcuntPage();
Assert.assertTrue(TARGETpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("--------Test steps executed----");
	}

}
