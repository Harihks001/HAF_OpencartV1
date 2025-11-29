package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;

public class TC_RG_001 extends BaseClass {

	// Method to Validate successful account registration
	@Test(groups = "registration")
	public void verifyAccountRegistartion() {
		// Step1: Click "My Account" → "Register"
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		// Step2:Fill all mandatory fields
		RegistrationPage rp = new RegistrationPage(driver);
		rp.registerFirstName("Test");
		rp.registerLastName("User001");
		//rp.registerEmail(randomEmail());
	    rp.registerEmail("User001@gmail.com");
		rp.registerTelephone(randomNumber());
		rp.registerPassword("Test@001");
		rp.registerConfirmPassword("Test@001");

		// Step3: Check Privacy Policy
		rp.agreeRegistrationTerms();
		// Step4:Click Continue
		rp.registerSubmit();

		// Step5:Validate successful account registration
		String confirmMessage = rp.getConfirmationMsg();
		Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");

	}

}
