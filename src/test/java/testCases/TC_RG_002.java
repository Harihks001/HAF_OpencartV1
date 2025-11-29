package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;

public class TC_RG_002 extends BaseClass{
	
	// Method to Validate registration with existing email

	@Test (dependsOnGroups = "registration")
	public void verifyAccountRegistartionwithExistingEmail() {
		// Step1: Click "My Account" → "Register"
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		// Step2:Fill all mandatory fields
		RegistrationPage rp = new RegistrationPage(driver);
		rp.registerFirstName("Test");
		rp.registerLastName("User002");
		rp.registerEmail("User001@gmail.com");//already registered email
		rp.registerTelephone(randomNumber());
		rp.registerPassword("Test@002");
		rp.registerConfirmPassword("Test@002");

		// Step3: Check Privacy Policy
		rp.agreeRegistrationTerms();
		// Step4:Click Continue
		rp.registerSubmit();

		// Step5:Validate Registration is not completed
		String confirmMessage = rp.getConfirmationMsg();
		Assert.assertNotEquals(confirmMessage, "Your Account Has Been Created!");
		
		//Validate if the error message is triggered
		String warningMessage = rp.getWarningMsg();
		Assert.assertEquals(warningMessage, "Warning: E-Mail Address is already registered!");
		

	}

}
