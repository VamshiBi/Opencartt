package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

// Run ALL
public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","master"})
    // Run | Debug
    public void verify_account_registration() throws InterruptedException {
    	logger.info("*****starting TC001_AccountRegistrationTest*****");
    	try {
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
    	logger.info("*****Clicked on Account*****");
// its a message to read
        hp.clickRegister();
    	logger.info("*****clicked on register*****");

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
    	logger.info("*****Filling customer details*****");

        regpage.setFirstName(randomString().toUpperCase());
        regpage.setLastName(randomString().toUpperCase());
       regpage.setEmail(randomString() + "@gmail.com"); // randomly generated the email
        regpage.setTelephone(randomNumber());

        String password = randomAlphaNumeric();

        regpage.setPassword(password);
        regpage.setConfirmPassword(password);

        regpage.setPrivacyPolicy();
        regpage.clickContinue();
    	logger.info("*****Validating expecting message*****");

        String confmsg = regpage.getConfirmationMsg();

        if(confmsg.equals("Your Account Has Been Created!"))
        {
        	Assert.assertTrue(true);
        }
        else
        //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        {
        	logger.error("Test failed...");
    		logger.debug("Debug logs...");
    		Assert.fail();
        }
        }
    	catch(Exception e)
    	{
  // we use debug(detailed) or info logs only , trace(more detailed)
    		System.out.println(e.getMessage());
    	}
logger.info("Test finished");
    }
}
