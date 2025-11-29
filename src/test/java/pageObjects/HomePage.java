package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}

//Locators and their webElements

	@FindBy(xpath = "//span[@class='caret']")
	WebElement myAccount;
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement register;
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement login;

//Method 1 to Click My account
	public void clickMyAccount() {

		myAccount.click();
	}

//Method 2 to Click Register
	public void clickRegister() {

		register.click();
	}

//Method 2 to Click Login
	public void clickLogin() {

		login.click();
	}

}
