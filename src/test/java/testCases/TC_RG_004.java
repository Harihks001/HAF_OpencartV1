package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;

public class TC_RG_004 extends BaseClass {

	// Method to Validate registration with invalid email format
	@Test
	public void verifyAccountRegistartionwithInvalidEmail() {
		// Step1: Click "My Account" → "Register"
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		// Step2:Capture the mandatory fields
		RegistrationPage rp = new RegistrationPage(driver);
		rp.registerFirstName("Test");
		rp.registerLastName("User004");
		rp.registerEmail("jujubi");// InvalidEmail Format
		rp.registerTelephone(randomNumber());
		rp.registerPassword("User004");
		rp.registerConfirmPassword("User004");

		// Step3: Check Privacy Policy
		rp.agreeRegistrationTerms();
		// Step4:Click Continue
		rp.registerSubmit();
		// Step5:Validate Registration is not completed
		String confirmMessage = rp.getConfirmationMsg();
		Assert.assertNotEquals(confirmMessage, "Your Account Has Been Created!");

		// Validate error message is for invalid email address
		System.out.println("Validation message: " + rp.gettooltipMsg());
		Assert.assertTrue(rp.gettooltipMsg().contains("Please"));

	}
}
