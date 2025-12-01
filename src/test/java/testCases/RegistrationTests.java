package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;

public class RegistrationTests extends BaseClass {

	// TC_RG_001 Validate successful account registration
	@Test(priority=1)
	public void verifyAccountRegistartion() {
		try {
			logger.info("Started TC_RG_001 Validate successful account registration");
			// Step1: Click "My Account" → "Register"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			logger.info("Navigated to Registration Page");

			// Capturing the auto generated details for future use
			String email = randomEmail();
			String password = "Test@001";
			String Lastname = randomString();
			String Telephone = randomNumber();
			// Step2:Fill all mandatory fields
			RegistrationPage rp = new RegistrationPage(driver);
			rp.registerFirstName("AutoReg");
			rp.registerLastName(Lastname);
			rp.registerEmail(email);
			rp.registerTelephone(Telephone);
			rp.registerPassword(password);
			rp.registerConfirmPassword(password);
			logger.info("Account registration form is filled");
			// Step3: Check Privacy Policy
			rp.agreeRegistrationTerms();
			// Step4:Click Continue
			rp.registerSubmit();

			// Step5:Validate successful account registration
			String confirmMessage = rp.getConfirmationMsg();
			Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
			logger.info("TC_RG_001 Passed");

			// LoggingOut for next Tests
			MyAccountPage mp = new MyAccountPage(driver);
			mp.clickLogout();
			// Printing New user credentials after registration
			logger.info("Fullname:  AutoReg " + Lastname);
			logger.info("Email: " + email);
			logger.info("Password used: " + password);
			logger.info("Generated Telephone: " + Telephone);

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_RG_002 Validate registration with existing email
	@Test(priority=2)
	public void verifyAccountRegistartionwithExistingEmail() {
		try {
			logger.info("Started TC_RG_002	Validate registration with existing email");
			// Step1: Click "My Account" → "Register"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			logger.info("Navigated to Registration Page");
			// Step2:Fill all mandatory fields
			RegistrationPage rp = new RegistrationPage(driver);
			rp.registerFirstName("Test");
			rp.registerLastName("User002");
			rp.registerEmail("User001@gmail.com");// already registered email
			rp.registerTelephone(randomNumber());
			rp.registerPassword("Test@002");
			rp.registerConfirmPassword("Test@002");
			logger.info("Account registration form is filled");
			// Step3: Check Privacy Policy
			rp.agreeRegistrationTerms();
			// Step4:Click Continue
			rp.registerSubmit();

			// Step5:Validate Registration is not completed
			String confirmMessage = rp.getConfirmationMsg();
			Assert.assertFalse(confirmMessage.equals("Your Account Has Been Created!"));
			logger.info("Warning message displayed");
			// Validate if the error message is triggered
			String warningMessage = rp.getWarningMsg();
			Assert.assertEquals(warningMessage, "Warning: E-Mail Address is already registered!");
			logger.info("TC_RG_002 Passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_RG_003 Validate registration with missing mandatory fields
	@Test(priority=3)
	public void verifyAccountRegistartionwithoutMandatiryFields() {

		try {
			logger.info("Started TC_RG_003 Validate registration with missing mandatory fields");
			// Step1: Click "My Account" → "Register"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();

			logger.info("Navigated to Registration Page");
			// Step2:Leave all mandatory fields as Blanks
			RegistrationPage rp = new RegistrationPage(driver);
			rp.registerFirstName(" ");
			rp.registerLastName(" ");
			rp.registerEmail(" ");
			rp.registerTelephone("");
			rp.registerPassword("");
			rp.registerConfirmPassword("");
			logger.info("Account registration form left blank");
			// Step3: Check Privacy Policy
			rp.agreeRegistrationTerms();
			// Step4:Click Continue
			rp.registerSubmit();
			// Step5:Validate Registration is not completed
			String confirmMessage = rp.getConfirmationMsg();
			Assert.assertNotEquals(confirmMessage, "Your Account Has Been Created!");

			// Validate if correct count of the error message is triggered
			// since 5 mandatory fields are left blank expected count is 5
			Assert.assertEquals(rp.getFieldErrorCount(), 5);

			// Validate the triggered error messages
			List<String> expectedErrors = Arrays.asList("First Name must be between 1 and 32 characters!",
					"Last Name must be between 1 and 32 characters!", "E-Mail Address does not appear to be valid!",
					"Telephone must be between 3 and 32 characters!", "Password must be between 4 and 20 characters!");
			Assert.assertEqualsNoOrder(rp.getErrorMessages(), expectedErrors);
			logger.info("TC_RG_003 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}
	}

	// TC_RG_004 Validate registration with invalid email format
	@Test(priority=4)
	public void verifyAccountRegistartionwithInvalidEmail() {
		try {
			logger.info("Started TC_RG_003 Validate registration with missing mandatory fields");
			// Step1: Click "My Account" → "Register"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			logger.info("Navigated to Registration Page");
			// Step2:Capture the mandatory fields
			RegistrationPage rp = new RegistrationPage(driver);
			rp.registerFirstName("Test");
			rp.registerLastName("User004");
			rp.registerEmail("jujubi");// InvalidEmail Format
			rp.registerTelephone(randomNumber());
			rp.registerPassword("User004");
			rp.registerConfirmPassword("User004");
			logger.info("Account registration form is filled");
			// Step3: Check Privacy Policy
			rp.agreeRegistrationTerms();
			// Step4:Click Continue
			rp.registerSubmit();
			// Step5:Validate Registration is not completed
			String confirmMessage = rp.getConfirmationMsg();
			Assert.assertFalse(confirmMessage.equals("Your Account Has Been Created!"));

			// Validate error message is for invalid email address
			System.out.println("Validation message: " + rp.gettooltipMsg());
			Assert.assertTrue(rp.gettooltipMsg().contains("Please"));
			logger.info("TC_RG_004 Passed");
			
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}
	
	//TC_RG_005	Validate registration with mismatched password and confirm password
	 @Test(priority=5)
	    public void verifyAccountRegistartionPasswordMismatch() {
	        logger.info("Started TC_RG_005 Validate registration with mismatched password and confirm password");

	        try {
	            // Step1: Click "My Account" → "Register"
	            logger.info("Navigating to registration page");
	            HomePage hp = new HomePage(driver);
	            hp.clickMyAccount();
	            hp.clickRegister();

	            // Step2: Fill mandatory fields
	            logger.info("Filling registration form with mismatched password data");
	            RegistrationPage rp = new RegistrationPage(driver);
	            rp.registerFirstName("Test");
	            rp.registerLastName("User005");

	            String email = randomEmail();
	            String phone = randomNumber();
	            logger.debug("Generated email: " + email);
	            logger.debug("Generated phone: " + phone);

	            rp.registerEmail(email);
	            rp.registerTelephone(phone);

	            // Step3: Enter valid password
	            rp.registerPassword("Test@123");
	            logger.debug("Entered password: Test@123");

	            // Step4: Enter mismatched confirm password
	            rp.registerConfirmPassword("Test@321");
	            logger.debug("Entered confirm password: Test@321");

	            // Step5: Agree to privacy policy
	            rp.agreeRegistrationTerms();
	            logger.info("Agreed to privacy policy");

	            // Step6: Submit the form
	            rp.registerSubmit();
	            logger.info("Submitted registration form");

	            // Step7: Validate registration is not completed
	            String confirmMessage = rp.getConfirmationMsg();
	            logger.debug("Confirmation message received: " + confirmMessage);
	            Assert.assertNotEquals(confirmMessage, "Your Account Has Been Created!");
	            logger.info("Registration failed as expected due to password mismatch");

	            // Validate error count
	            int errorCount = rp.getFieldErrorCount();
	            logger.debug("Field error count: " + errorCount);
	            Assert.assertEquals(errorCount, 1, "Expected one error message for password mismatch");

	            // Validate error message content
	            List<String> expectedError = Arrays.asList("Password confirmation does not match password!");
	            List<String> actualErrors = rp.getErrorMessages();
	            logger.debug("Actual error messages: " + actualErrors);
	            Assert.assertEqualsNoOrder(actualErrors, expectedError);
	            logger.info("TC_RG_005 Passed");

	        } catch (Exception e) {
	            logger.error("Test failed due to unexpected exception", e);
	            Assert.fail("Test encountered an exception: " + e.getMessage());
	        }
	    }

}
