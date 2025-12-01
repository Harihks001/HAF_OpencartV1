package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WhishListPage extends BasePage {
	
	public WhishListPage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}
	
	//Locators and their webElements
	@FindBy(xpath="//h2[normalize-space()='My Wish List']") 
	WebElement myWishListTitle;

	   //Method to verify My wish list page Title is Displayed
		public boolean ismyWishListTitleDisplayed() {

			try {
				return (myWishListTitle.isDisplayed());
			} catch (Exception e) {
				return false;
			}
		}

}
