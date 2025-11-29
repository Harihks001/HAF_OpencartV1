package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtils;

public class TC_RG_DDT extends BaseClass {

	// Method to Validate successful account registration
	@Test(dataProvider = "DP_RegisterData", dataProviderClass = DataProviders.class)
	public void verifyBulkAccountRegistartion(String FirstName, String LastName, String Email, String Telephone,
			String Password, int rowIndex) throws IOException {

		logger.info("Test Started");
		// Defining Objects for all pages required in this Test
		HomePage hp = new HomePage(driver);
		RegistrationPage rp = new RegistrationPage(driver);
		ExcelUtils xl = new ExcelUtils(".\\testData\\UserAccounts.xlsx");
		String outcome = null;

		try {
			// Step1: Click "My Account" → "Register"
			hp.clickMyAccount();
			hp.clickRegister();

			// Step2:Fill all mandatory fields

			rp.registerFirstName(FirstName);
			rp.registerLastName(LastName);
			rp.registerEmail(Email);
			rp.registerTelephone(Telephone);
			rp.registerPassword(Password);
			rp.registerConfirmPassword(Password);

			// Step3: Check Privacy Policy
			rp.agreeRegistrationTerms();
			// Step4:Click Continue
			rp.registerSubmit();

			// Step5:Validate successful account registration
			boolean result = rp.isConfirmationMsgDisplayed();
			boolean alreadyregisterdMsg = rp.isAlrRegMsgDisplayed();
			if (result == true) {
				rp.clickLogoutBtn();
				logger.info("Successfully Registered");
				outcome = "Success";
				xl.setCellData("TestUsers", rowIndex, 5, outcome);
				Assert.assertTrue(true);

			} else if(alreadyregisterdMsg==true) {
				logger.info("Already Registered");
				outcome = "Already registered";
				xl.setCellData("TestUsers", rowIndex, 5, outcome);
				Assert.assertTrue(true);
			}
			
			else {
				logger.info("Registeration was unsuccessfull");
				outcome = "Failure";
				xl.setCellData("TestUsers", rowIndex, 5, outcome);
				Assert.assertTrue(false);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
