package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver)

	{
		// Invoke BasePage driver
		super(driver);
	}

	// Locators and their webElements
	@FindBy(xpath = "//h1[normalize-space()='Search - Macbook']")
	WebElement searchTitle;
	@FindBy(xpath = "//div[@class='caption']//a")
	private List<WebElement> searchProducts;
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	WebElement thereIsNoProductThatMatch;
	@FindBy(xpath = "(//div[@class='product-thumb'])[1]//div[@class='image']")
	WebElement productImage;
	@FindBy(xpath = "//div[@id='content']//h1")
	WebElement productTitle;
	@FindBy(xpath = "//div[@id='product-product']//div[@class='btn-group']//button[1]")
	WebElement Whishlistbtn;
	@FindBy(xpath = "//div[@id='product-product']//div[@class='btn-group']//button[2]")
	WebElement combarisonbtn;
	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement addToCartbtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']//a[2]")
	WebElement successMsg;
	@FindBy(xpath="//button[normalize-space()='×']") 
	WebElement clearMsgbtn;

	// Method to validate Search title
	public boolean validateSearchTitleContains(String item) {
		try {
			return (searchTitle.getText().contains(item));
		} catch (Exception e) {
			return false;
		}
	}

	// Method to validate Search product
	public boolean validateSearchResultsContain(String item) {
		if (searchProducts.isEmpty()) {
			System.out.println("No products found for keyword: " + item);
			return false;
		}

		for (WebElement caption : searchProducts) {
			String text = caption.getText().toLowerCase();
			if (!text.contains(item.toLowerCase())) {
				System.out.println("Unexpected product found: " + caption.getText());
				return false;
			}
		}

		return true;
	}

	// Method to verify no products displayed
	public boolean validateNoProductDisplayed() {
		try {
			return (thereIsNoProductThatMatch.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	// Method to click the Product image
	public void cickProduct() {
		productImage.click();
	}

	// Method to verify no products displayed
	public boolean validateProductDisplayed(String item) {
		try {
			return (productTitle.getText().equalsIgnoreCase(item));
		} catch (Exception e) {
			return false;
		}
	}

	// Method to click the Product image
	public void addtoWhislist() {
		Whishlistbtn.click();
	}

	// Method to click the Product image
	public void addforComparison() {
		combarisonbtn.click();
	}

	// Method to click the Product image
	public void addToCart() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(addToCartbtn));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartbtn);
		    addToCartbtn.click();


	}

	// Method to get the Confirmation Message
	public String getConfirmMessage() {
		return (successMsg.getText());
	}
	
	// Method to clear the Confirmation Message
		public void clearConfirmMessage() {
			clearMsgbtn.click();
		}
}
