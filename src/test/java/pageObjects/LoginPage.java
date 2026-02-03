package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
WebDriver driver;
public LoginPage(WebDriver qa)
{
	super(qa);
}
//Locators
@FindBy(xpath="//input[@id='input-email']") WebElement ipEmail;
@FindBy(xpath="//input[@id='input-password']") WebElement ipPwd;
@FindBy(xpath="//input[@value='Login']")WebElement ck_Login;

public void EnterMail(String mail)
{
	ipEmail.sendKeys(mail);
}
public void EnterPWD(String pwd)
{
	ipPwd.sendKeys(pwd);
}
public void ClickLogiN()
{
	ck_Login.click();
}

}
