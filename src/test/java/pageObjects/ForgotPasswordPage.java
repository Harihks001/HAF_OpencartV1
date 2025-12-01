package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {

	public ForgotPasswordPage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}

	// Locators and their webElements

	@FindBy(xpath = "//h1[normalize-space()='Forgot Your Password?']")
	WebElement forgotYourPasswordTitle;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement eMailAddress;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continuebtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement EmailmsgsendConfirmation;

	// Method to Capture Email address
	public boolean isForgotPaswrdTitleDisplayedd() {

		try {
			return (forgotYourPasswordTitle.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	// Method to capture the email address
	public void captureEmail(String Email) {
		eMailAddress.sendKeys(Email);
	}

	// Method to Click continue button
	public void clickContinue() {
		continuebtn.click();
	}

	// Method to Click continue button
	public boolean EmailConfirmationDisplayed() {
		try {
			return (EmailmsgsendConfirmation.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
}
