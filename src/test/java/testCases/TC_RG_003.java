package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;

public class TC_RG_003 extends BaseClass{
	
	// Method to Validate registration with missing mandatory fields


	@Test
	public void verifyAccountRegistartionwithoutMandatiryFields() {
		// Step1: Click "My Account" → "Register"
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		// Step2:Leave all mandatory fields as Blanks
		RegistrationPage rp = new RegistrationPage(driver);
		rp.registerFirstName(" ");
		rp.registerLastName(" ");
		rp.registerEmail(" ");
		rp.registerTelephone("");
		rp.registerPassword("");
		rp.registerConfirmPassword("");

		// Step3: Check Privacy Policy
		rp.agreeRegistrationTerms();
		// Step4:Click Continue
		rp.registerSubmit();

		// Step5:Validate Registration is not completed
		String confirmMessage = rp.getConfirmationMsg();
		Assert.assertNotEquals(confirmMessage, "Your Account Has Been Created!");
		
		//Validate if correct count of the error message is triggered
		//since 5 mandatory fields are left blank expected count is 5
		Assert.assertEquals(rp.getFieldErrorCount(), 5);
		
		//Validate the triggered error messages
		List<String> expectedErrors = Arrays.asList(
			    "First Name must be between 1 and 32 characters!",
			    "Last Name must be between 1 and 32 characters!",
			    "E-Mail Address does not appear to be valid!",
			    "Telephone must be between 3 and 32 characters!",
			    "Password must be between 4 and 20 characters!"
			);
		Assert.assertEqualsNoOrder(rp.getErrorMessages(), expectedErrors);



	}

}
