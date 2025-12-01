package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}

//Locators and their webElements

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement eMailAddress;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	@FindBy(xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
	WebElement forgottenPassword;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginbtn;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement NoMatchForCredentials;

//Method 1 to Capture Email address
	public void setEmail(String Email) {

		eMailAddress.sendKeys(Email);
	}

//Method 2 to Capture Password
	public void setPassword(String pwd) {

		password.sendKeys(pwd);
	}

//Method 3 to Click Login
	public void clickLoginbtn() {

		loginbtn.click();
	}

//Method 4 to Click forgot Password
	public void clickForgotPwrd() {

		forgottenPassword.click();
	}

//Method 5 get the message if credentials are wrong
	public String getInvalidDetailsMsg() {
		try {
			return NoMatchForCredentials.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
//Method 6 
//Method 7 
//Method 8 

//Method 9
//Method 10 
}
