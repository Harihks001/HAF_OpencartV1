package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBases.BaseClass;
//import testBases.SeleniumGridBaseClass;
import utilities.DataProviders;
import utilities.ExcelUtils;

public class TC_LG_DDT extends BaseClass {

	@Test(dataProvider = "DP_LoginData", dataProviderClass = DataProviders.class)
	public void LoginDDT(String Email, String Password, String Validity, String status, int rowIndex) {

		String outcome = null;
		ExcelUtils xlutil = new ExcelUtils(".\\testData\\UserAccounts.xlsx");
		logger.info("Test Started");
		try {
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			// logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			// logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(Email);
			lp.setPassword(Password);

			// Step3:Click Login button
			lp.clickLoginbtn();
			// logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			MyAccountPage mp = new MyAccountPage(driver);
			boolean result = mp.isMyAccountDisplayed();

			// Valid Data>Successful login>Test Passed
			// Valid Data>Unsuccessful login>Test Failed
			if (Validity.equalsIgnoreCase("Valid")) {
				if (result == true) {
					mp.clickLogout(); // once login is successful click logout for next verification
					logger.info("Successfully Logged in with valid Data");
					outcome = "Pass";
					xlutil.setCellData("LoginData", rowIndex, 5, outcome);
					xlutil.fillGreen("LoginData", rowIndex, 5);
					Assert.assertTrue(true);
				} else {
					logger.info("Login was Unsuccessful even with valid Data");
					outcome = "Fail";
					xlutil.setCellData("LoginData", rowIndex, 5, outcome);
					xlutil.fillRed("LoginData", rowIndex, 5);
					Assert.assertTrue(false);
				}
			}

			// Invalid Data>Successful login>Test Failed
			// Invalid Data>Unsuccessful login>Test Passed
			if (Validity.equalsIgnoreCase("Invalid")) {
				if (result == false) {
					logger.info("Login was Unsuccessful with Invalid Data");
					outcome = "Pass";
					xlutil.setCellData("LoginData", rowIndex, 5, outcome);
					xlutil.fillGreen("LoginData", rowIndex, 5);
					Assert.assertTrue(true);
				} else {
					mp.clickLogout(); // once login is successful click logout for next verification
					logger.info("Successfully Logged in even with Invalid Data");
					outcome = "Fail";
					xlutil.setCellData("LoginData", rowIndex, 5, outcome);
					xlutil.fillRed("LoginData", rowIndex, 5);
					Assert.assertTrue(false);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		logger.info("TC_LG_DDT Test was completed");

	}
}
