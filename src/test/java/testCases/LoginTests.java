package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.WhishListPage;
import testBases.BaseClass;
//import testBases.SeleniumGridBaseClass;

public class LoginTests extends BaseClass {

	// TC_LG_001 Validate successful login
	@Test(priority = 1)
	public void verifyAccountLogin() {

		try {
			logger.info("Started the Test TC_LG_001 Validate successful login");
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
			logger.info("Test TC_LG_001 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_002 Validate login with invalid password
	@Test(priority = 4)
	public void validateLoginwithInvalidPasswrd() {

		try {
			logger.info("Started the Test TC_LG_002	Validate login with invalid password");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(P.getProperty("e_mail"));
			lp.setPassword(P.getProperty("invalid_pswrd"));// invalid password
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			String result = lp.getInvalidDetailsMsg();
			Assert.assertEquals(result, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test TC_LG_002 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_003 Validate login with unregistered email
	@Test(priority = 5)
	public void validateLoginwithInvalidEmail() {

		try {
			logger.info("Started the Test TC_LG_003	Validate login with unregistered email");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(P.getProperty("invalid_email"));// invalid Email
			lp.setPassword(P.getProperty("pass_word"));// correct password
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			String result = lp.getInvalidDetailsMsg();
			Assert.assertEquals(result, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test TC_LG_003 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_004 Validate login with empty email field
	@Test(priority = 6)
	public void validateLoginwithEmptyEmail() {

		try {
			logger.info("Started the Test TC_LG_004	Validate login with empty email field");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(" ");// empty Email
			lp.setPassword(P.getProperty("pass_word"));// correct password
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			String result = lp.getInvalidDetailsMsg();
			Assert.assertEquals(result, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test TC_LG_004 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_005 Validate login with empty password field
	@Test(priority = 7)
	public void validateLoginwithEmptyPassword() {

		try {
			logger.info("Started the Test TC_LG_005	Validate login with empty password field");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(P.getProperty("e_mail"));// correct Email
			lp.setPassword("");// empty password
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			String result = lp.getInvalidDetailsMsg();
			Assert.assertEquals(result, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test TC_LG_005 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_006 Validate login with both fields empty
	@Test(priority = 8)
	public void validateLoginwithEmptycredentials() {

		try {
			logger.info("Started the TC_LG_006	Validate login with both fields empty");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail("");// empty Email
			lp.setPassword("");// empty password
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			String result = lp.getInvalidDetailsMsg();
			Assert.assertEquals(result, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test TC_LG_006 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_007 Validate login with invalid email format
	@Test(priority = 9)
	public void validateLoginwithInvalidEmailFormat() {

		try {
			logger.info("Started the TC_LG_007	Validate login with invalid email format");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Capture credentials
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail("User001@gmail");// invalid Email
			lp.setPassword(P.getProperty("pass_word"));// empty password
			// Step3:Click Login button
			lp.clickLoginbtn();
			logger.info("Clicked Login button");
			// Step4: verify user logged in successfully
			String result = lp.getInvalidDetailsMsg();
			Assert.assertEquals(result, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test TC_LG_007 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_008 Validate login session persistence
	@Test(dependsOnMethods = { "verifyAccountLogin" }, priority = 2)
	public void validateLoginPersistence() {

		try {
			logger.info("Started the TC_LG_008	Validate login session persistence");
			// TC_LG_001 should be passed
			// Step1: Click "Wish list" after login
			MyAccountPage mp = new MyAccountPage(driver);
			mp.clickWishList();
			logger.info("Navigating to wishlist page");
			// Step2: Validate the Wish list title is displayed
			WhishListPage wp = new WhishListPage(driver);
			boolean result1 = wp.ismyWishListTitleDisplayed();
			Assert.assertEquals(result1, true, "Whislist page is not displayed");
			// Step3:Return to "My Account" page
			logger.info("Navigating back to the account Page");
			driver.navigate().back();
			// Step3: verify user is still logged in by checking My Account title
			boolean result = mp.isMyAccountDisplayed();
			Assert.assertEquals(result, true, "My Account page is not displayed");
			logger.info("Test TC_LG_008 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_LG_009 Validate logout after login
	@Test(dependsOnMethods = { "verifyAccountLogin"}, priority = 3)
	public void validateLogOut() {

		try {
			logger.info("Started the Test TC_LG_009	Validate logout after login");
			// TC_LG_001 should be passed
			// Step1: Click "Log Out"
			MyAccountPage mp = new MyAccountPage(driver);
			mp.clickLogout();
			logger.info("Clicked Logout button");
			// Step4: verify user logged in successfully
			boolean result = mp.isAcclogoutDisplayed();
			Assert.assertEquals(result, true, "Logout Failed");
			logger.info("Test TC_LG_009 passed");
		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}
	
	//TC_LG_010	Validate Forgot Password functionality
	@Test(priority = 10)
	public void verifyForgotPasswordFunctn() {

		try {
			logger.info("Started the Test TC_LG_010	Validate Forgot Password functionality");
			// Step1: Click "My Account" → "Login"
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();

			// Step2: Click Forgot password link
			LoginPage lp = new LoginPage(driver);
			lp.clickForgotPwrd();
			logger.info("Forgot password link clicked");
			
			// Step3: verify user is in Forgot password page
			ForgotPasswordPage fp = new ForgotPasswordPage(driver);
			boolean result = fp.isForgotPaswrdTitleDisplayedd();
			Assert.assertEquals(result, true, "Forgot Password title is not displayed");
			
			//Step 4: Capture the registered email address
			fp.captureEmail("Auto002@mail.com");
			//Step5: click continue
			fp.clickContinue();
			logger.info("Entered the email address");
			//Step6: Verify the confirmation
			boolean result2 = fp.EmailConfirmationDisplayed();
			Assert.assertEquals(result2, true, "Email Confirmation is not displayed");
			logger.info("Test TC_LG_010 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}
}
