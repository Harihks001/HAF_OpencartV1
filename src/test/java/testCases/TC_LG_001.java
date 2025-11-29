package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBases.BaseClass;
//import testBases.SeleniumGridBaseClass;

public class TC_LG_001 extends BaseClass {

	@Test
	public void verifyAccountLogin() {

		try {
			logger.info("Test Started");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(P.getProperty("e_mail"));
			lp.setPassword(P.getProperty("pass_word"));
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			MyAccountPage mp = new MyAccountPage(driver);
			boolean result = mp.isMyAccountDisplayed();
			Assert.assertEquals(result, true, "Login Failed");
			logger.info("Successfully Logged in");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}
}
