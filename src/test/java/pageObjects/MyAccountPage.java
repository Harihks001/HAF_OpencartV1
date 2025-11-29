package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}

//Locators and their webElements

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logoutbtn;
	@FindBy(xpath = "//h1[normalize-space()='Account Logout']")
	WebElement accountLogout;//even though this is on another page just because of only one, adding it here

   //Method to Capture Email address
	public boolean isMyAccountDisplayed() {

		try {
			return (myAccount.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	// Method to Click Logout
	public void clickLogout() {

		logoutbtn.click();
	}

	// Method to validate successful logout
	public boolean isAcclogoutDisplayed() {

		try {
			return (accountLogout.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

}