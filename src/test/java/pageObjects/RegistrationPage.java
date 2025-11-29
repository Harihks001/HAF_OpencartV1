package pageObjects;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}

//Locators and their webElements

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstName;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastName;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement eMail;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephone;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement passwordConfirm;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement agree;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueBtn;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement RegisterSuccess;
	@FindBy(xpath = "//*[@class='alert alert-danger alert-dismissible']")
	WebElement RegisterUnSuccess;
	@FindBy(xpath = "//*[@class='text-danger']")
	private List<WebElement> fieldErrors;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement LogoutBtn;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") 
	WebElement EmailAddressIsAlr;

//Method 1 to Capture First name
	public void registerFirstName(String firstname) {

		firstName.sendKeys(firstname);
	}

//Method 2 to Capture Last name
	public void registerLastName(String lastname) {

		lastName.sendKeys(lastname);
	}

//Method 3 to Capture Email Address
	public void registerEmail(String Email) {

		eMail.sendKeys(Email);
	}

//Method 4 to Capture phone number
	public void registerTelephone(String phonenumber) {

		telephone.sendKeys(phonenumber);
	}

//Method 5 to Capture Password
	public void registerPassword(String passcode) {

		password.sendKeys(passcode);
	}

//Method 6 to Confirm password
	public void registerConfirmPassword(String ConfirmPassword) {

		passwordConfirm.sendKeys(ConfirmPassword);
	}

//Method 7 to Agree terms and Conditions
	public void agreeRegistrationTerms() {

		agree.click();
	}

//Method 8 to Continue to next page
	public void registerSubmit() {

		continueBtn.click();
	}

//Method 9 to get confirmation message
	public String getConfirmationMsg() {
		try {
			return RegisterSuccess.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

//Method 10 to get warning message
	public String getWarningMsg() {
		try {
			return RegisterUnSuccess.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

//Method 11 to get all the mandatory fields error messages
	public List<String> getErrorMessages() {
		List<String> messages = new ArrayList<>();
		for (WebElement error : fieldErrors) {
			messages.add(error.getText());
		}
		return messages;
	}

// Method 12 to get the count of mandatory fields error messages
	public int getFieldErrorCount() {
		return fieldErrors.size();
	}

//Method 13 to get tool tip message for invalid email
	public String gettooltipMsg() {
		try {
			return eMail.getAttribute("validationMessage");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// Method to verify confirmMsg is displayed
	public boolean isConfirmationMsgDisplayed() {
		try {
			return RegisterSuccess.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	//Method to click Logout Button once registered
	public void clickLogoutBtn() {
		LogoutBtn.click();
	}
	
	// Method to verify already registered message is displayed
		public boolean isAlrRegMsgDisplayed() {
			try {
				return EmailAddressIsAlr.isDisplayed();
			} catch (Exception e) {
				return false;
			}
		}

}
