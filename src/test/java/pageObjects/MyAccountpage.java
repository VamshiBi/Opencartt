package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountpage extends BasePage{
WebDriver driver;
public MyAccountpage(WebDriver driver)
{
	super(driver);
}


@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement Acpage;
@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lgout;
public boolean MyAcuntPage()
{
	try {
		return Acpage.isDisplayed();
		
	}
	catch(Exception e)
	{
		return false;
		
	}
}

public void logoutAc()
{
	lgout.click();
}
}
