package tests;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SauceDemoCartPage;
import pages.SauceDemoLoginPage;
import pages.SauceDemoProductsPage;

public class SauceDemoTest extends Base {

	WebDriver driver;
	SauceDemoLoginPage sdLoginPage;
	SauceDemoProductsPage sdProductPage;

	@BeforeMethod
	public void setup() {
		driver = LaunchBrowser();
	}

	@AfterMethod
	public void tearDown() {
		closeBrowserInstances();
	}

	@Test
	public void tc_001() {

		String url = "https://www.saucedemo.com/";
		String username = "standard_user";
		String password = "secret_sauce";
		String[] items = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket" };
		String firstName = "Mahesh";
		String lastName = "Babu";
		String postalCode = "12345";

		driver.get(url);
		sdLoginPage = new SauceDemoLoginPage(driver);
		sdLoginPage.enterUserName(username);
		sdLoginPage.enterPassword(password);
		sdLoginPage.clickOnLoginButton();

		SauceDemoProductsPage sdProductPage = new SauceDemoProductsPage(driver);
		sdProductPage.addProductToCart(items);
		Assert.assertTrue(sdProductPage.noOfItemsAddedInCart() == items.length, "Addcart Display number wrong");
		sdProductPage.clickOnShopingCartIcon();
		SauceDemoCartPage sdcartPage = new SauceDemoCartPage(driver);
		Assert.assertTrue(sdcartPage.productsAddedToCart().equals(Arrays.asList(items)), "Items not matched");
		sdcartPage.clickOnCheckOutButton();
		sdcartPage.enterFirstName(firstName);
		sdcartPage.enterLastName(lastName);
		sdcartPage.enterPostalCode(postalCode);
		sdcartPage.clickOnContinueButton();
		Assert.assertTrue(sdcartPage.comparePrice(), "Price not matched");
		sdcartPage.clickOnFinishButton();
		Assert.assertEquals(sdcartPage.getCheckOutSuccessConfiramationMessage(), "Thank you for your order!");
		sdcartPage.clickOnBurgerMenuIcon();
		sdcartPage.clickOnLogoutOption();
		Assert.assertTrue(getCurrentPageUrl().equals(url), "Logout unsuccessful");
	}

}

//URL: https://www.saucedemo.com/ 
//Credentials: * Username: standard_user
//Password: secret_sauce

//Step 1: Login & Security:

//Navigate to the URL.
//Enter the username and password using sendKeys.
//Click the "Login" button.
//Validation: Verify the URL changes to includes /inventory.html and the page header "Products" is visible.

//Step 2: Dynamic Product Interaction:

//Challenge: Do not click the first product. 
//Instead, find the product named "Sauce Labs Backpack" 
//specifically by its text and click its "Add to cart" button.
//Find the product named "Sauce Labs Fleece Jacket" and click its "Add to cart" button.
//Validation: Verify the Shopping Cart badge (top right) displays the number 2.

//Step 3: Cart Management (List Handling):

//Click on the Shopping Cart icon.
//Validation: Use findElements to create a list of the items in the cart.
//Verify that both products you added are present in the list.
//Click the "Checkout" button.

//Step 4: Data Entry & Finish
//Fill out the "Checkout: Your Information" form (First Name, Last Name, Zip Code).
//Click "Continue."
//Validation (The "Array" Practice): Extract the Item Total price and the Tax price from the summary.
//Convert them to numbers in Java and verify that Total = Item Total + Tax.
//Click "Finish."

//Step 5: Completion & Logout

//Validation: Verify the header "Thank you for your order!" is displayed.
//Click the "Burger Menu" (top left).
//Synchronization Challenge: Use an Explicit Wait to wait for the "Logout" link 
//to become visible (it has an animation), then click it.
//Final Validation: Verify you are redirected back to the login page.
