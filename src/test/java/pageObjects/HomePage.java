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
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement search;
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement searchbtn;

//Method to Click My account
	public void clickMyAccount() {

		myAccount.click();
	}

//Method to Click Register
	public void clickRegister() {

		register.click();
	}

//Method to Click Login
	public void clickLogin() {

		login.click();
	}

	// Method to Search items
	public void setSearchas(String item) {

		search.sendKeys(item);
	}
	
	// Method to Clear Search 
		public void clearSearch() {

			search.clear();
		}

	// Method to Click Search
	public void clickSearch() {

		searchbtn.click();
	}

}
