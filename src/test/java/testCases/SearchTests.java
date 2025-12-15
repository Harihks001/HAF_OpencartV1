package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import testBases.BaseClass;

public class SearchTests extends BaseClass {

	// TC_SR_001 Validate Search function
	@Test(priority = 1)
	public void verifySearchFnctn() {

		try {
			logger.info("Started the Test TC_SG_001 Validate Search function");
			// Step1: Capture Item in the Search and click Search
			HomePage hp = new HomePage(driver);
			// Login First as Prerequisite
			hp.clickMyAccount();
			logger.info("Navigating to Login page");
			hp.clickLogin();
			logger.info("Filling Login data");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(P.getProperty("e_mail"));
			lp.setPassword(P.getProperty("pass_word"));
			lp.clickLoginbtn();
			logger.info("Clicked Login button");

			// Step1:Search for the product
			hp.clearSearch();// clear the search first
			hp.setSearchas(P.getProperty("product"));
			hp.clickSearch();
			logger.info("Clicked Search");

			// Step2: Verify correct items is shown after search
			SearchPage sp = new SearchPage(driver);
			boolean result = sp.validateSearchResultsContain(P.getProperty("keyword"));
			Assert.assertTrue(result, "Search results do not match keyword");
			logger.info("Test TC_SR_001 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_002 Validate search with partial product name
	@Test(priority = 6)
	public void verifySearchforPartialName() {

		try {
			logger.info("Started the Test TC_SG_002 Validate search with partial product name");
			// Step1: Capture Item in the Search and click Search
			HomePage hp = new HomePage(driver);
			hp.clearSearch();// clear the search first
			hp.setSearchas("Mac");
			hp.clickSearch();
			logger.info("Clicked Search");

			// Step2: Verify correct items is shown after search
			SearchPage sp = new SearchPage(driver);
			boolean result = sp.validateSearchResultsContain(P.getProperty("keyword"));
			Assert.assertTrue(result, "Search results do not match keyword");
			logger.info("Test TC_SR_002 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_003 Validate search with case-insensitive input
	@Test(priority = 7)
	public void verifySearchforCaseSensitivity() {

		try {
			logger.info("Started the Test TC_SG_003 Validate search with case-insensitive input");
			// Step1: Capture Item in the Search and click Search
			HomePage hp = new HomePage(driver);
			hp.clearSearch();// clear the search first
			hp.setSearchas("macBook");
			hp.clickSearch();
			logger.info("Clicked Search");

			// Step2: Verify correct items is shown after search
			SearchPage sp = new SearchPage(driver);
			boolean result = sp.validateSearchResultsContain(P.getProperty("keyword"));
			Assert.assertTrue(result, "Search results do not match keyword");
			logger.info("Test TC_SR_003 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_004 Validate search with special characters
	@Test(priority = 8)
	public void verifySearchforSpecialCharacters() {

		try {
			logger.info("Started the Test TC_SR_004 Validate search with special characters");
			// Step1: Capture Item in the Search and click Search
			HomePage hp = new HomePage(driver);
			hp.clearSearch();// clear the search first
			hp.setSearchas("mac@Book");
			hp.clickSearch();
			logger.info("Clicked Search");

			// Step2: Verify No Products should be displayed
			SearchPage sp = new SearchPage(driver);
			boolean result = sp.validateNoProductDisplayed();
			Assert.assertTrue(result, "Search results Displayed ignoring Special Characters");
			logger.info("Test TC_SR_004 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_005 Validate search with empty input
	@Test(priority = 9)
	public void verifySearchforEmptyInput() {

		try {
			logger.info("Started the Test TC_SR_005 Validate search with empty input");
			// Step1: Capture Item in the Search and click Search
			HomePage hp = new HomePage(driver);
			hp.clearSearch();// clear the search first
			hp.setSearchas(" ");// give empty Input
			hp.clickSearch();
			logger.info("Clicked Search");

			// Step2: Verify No Products should be displayed
			SearchPage sp = new SearchPage(driver);
			boolean result = sp.validateNoProductDisplayed();
			Assert.assertTrue(result, "Search results Displayed ignoring Empty item");
			logger.info("Test TC_SR_005 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_006 Validate search result navigation
	@Test(dependsOnMethods = { "verifySearchFnctn" }, priority = 2)
	public void verifySearchResultNavigation() {

		try {
			logger.info("TC_SR_006 Validate search result navigation");
			// TC_SR_001 should be passed
			// Step1: Click on the displayed product
			SearchPage sp = new SearchPage(driver);
			sp.cickProduct();
			// Step2: Verify user is directed to correct product page
			boolean result = sp.validateProductDisplayed(P.getProperty("product"));
			Assert.assertTrue(result, "Product displayed do not match");
			logger.info("Test TC_SR_006 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_007 Validate "Add to Cart" from search results
	@Test(dependsOnMethods = { "verifySearchResultNavigation" }, priority = 5)
	public void validateAddtoCart() {

		try {
			logger.info("TC_SR_007 Validate Add to Cart from search results");
			// TC_SR_006 should be passed
			// Step1: Click on the ADD to Cart button
			SearchPage sp = new SearchPage(driver);
			sp.addToCart();
			logger.info("Clicked Add to Cart button");
			Thread.sleep(900);
			// Step2: Verify the confirmation message
			String message = sp.getConfirmMessage();
			Assert.assertEquals(message, "shopping cart");
			logger.info("Test TC_SR_007 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_008 Validate "Add to Wish List" from search results
	@Test(dependsOnMethods = { "verifySearchResultNavigation" }, priority = 3)
	public void validateAddtoWhislist() {

		try {
			logger.info("TC_SR_008 Validate Add to Wish List from search results");
			// TC_SR_006 should be passed
			// Step1: Click on the ADD to WishList button
			SearchPage sp = new SearchPage(driver);
			sp.addtoWhislist();
			logger.info("Clicked Add to Wishlist button");
			// Step2: Verify the confirmation message
			String message = sp.getConfirmMessage();
			Assert.assertEquals(message, "wish list");
			sp.clearConfirmMessage();
			logger.info("Test TC_SR_008 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

	// TC_SR_009 Validate "Compare this Product" from search results
	@Test(dependsOnMethods = { "verifySearchResultNavigation" }, priority = 4)
	public void validateAddtoComparison() {

		try {
			logger.info("TC_SR_009 Validate Compare this Product from search results");
			// TC_SR_006 should be passed
			// Step1: Click on the Compare this Product button
			SearchPage sp = new SearchPage(driver);
			sp.addforComparison();
			logger.info("Clicked Compare this Product button");
			Thread.sleep(200);
			// Step2: Verify the confirmation message
			String message = sp.getConfirmMessage();
			Assert.assertEquals(message, "product comparison");
			sp.clearConfirmMessage();
			logger.info("Test TC_SR_009 Passed");

		} catch (Exception e) {
			logger.error("Test failed due to unexpected exception", e);
			Assert.fail("Test encountered an exception: " + e.getMessage());
		}

	}

}
