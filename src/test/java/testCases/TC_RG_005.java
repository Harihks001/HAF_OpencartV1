package testCases;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;

public class TC_RG_005 extends BaseClass {

    // Method to Validate registration with mismatched password and confirm password
    @Test
    public void verifyAccountRegistartionPasswordMismatch() {
        logger.info("Starting test: verifyAccountRegistartionPasswordMismatch");

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
            logger.info("Error message validated successfully");

        } catch (Exception e) {
            logger.error("Test failed due to unexpected exception", e);
            Assert.fail("Test encountered an exception: " + e.getMessage());
        }

        logger.info("Test completed: verifyAccountRegistartionPasswordMismatch");
    }
}