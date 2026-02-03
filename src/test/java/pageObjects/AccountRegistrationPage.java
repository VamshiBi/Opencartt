package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.testng.annotations.*;

public class AccountRegistrationPage extends BasePage{
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver) //constructor 
	{
		super(driver);
	}
	//Locators
@FindBy(xpath="//input[@id='input-firstname']") WebElement first_name;
@FindBy(xpath="//input[@id='input-lastname']") WebElement last_name;
@FindBy(xpath="//input[@id='input-email']") WebElement email;
@FindBy(xpath="//input[@id='input-telephone']") WebElement contact;
@FindBy(xpath="//input[@id='input-password']") WebElement ip_password;
@FindBy(xpath="//input[@id='input-confirm']") WebElement cf_password;
@FindBy(xpath="//input[@name='agree']") WebElement agree_btn;
@FindBy(xpath="//input[@value='Continue']") WebElement Continue_btn;
@FindBy(xpath="//h1[contains(.,'Account')]") WebElement conf_msg;

public void setFirstName(String fname)
{
	first_name.sendKeys(fname);
}
public void setLastName(String lname)
{
	last_name.sendKeys(lname);
}

public void setEmail(String mail)
{
	email.sendKeys(mail);
	System.out.println(mail);
	
}
public void setTelephone(String phn)
{
	
	contact.sendKeys(phn);
}

public void setPassword(String pwd)
{
	ip_password.sendKeys(pwd);
	System.out.println(pwd);
}
public void setConfirmPassword(String pwd)
{
	cf_password.sendKeys(pwd);
}

public void setPrivacyPolicy()
{
	agree_btn.click();
}
public void clickContinue()
{
	Continue_btn.click();
}

public String getConfirmationMsg() throws InterruptedException
{
	Thread.sleep(3000);
    try {
        return conf_msg.getText();   // return the element text
    }
    catch(Exception e)
    {
        return e.getMessage();      // return exception message if something fails
    }
}


}