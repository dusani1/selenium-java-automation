package com.day1.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceDemo {

//	URL: https://www.saucedemo.com/ 
//	Credentials: * Username: standard_user
//	Password: secret_sauce

	public static void main(String[] args) throws InterruptedException {
//		Step 1: Login & Security:
//		Navigate to the URL.
//		Enter the username and password using sendKeys.
//		Click the "Login" button.
//		Validation: Verify the URL changes to includes /inventory.html and the page header "Products" is visible.
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
//		Step 2: Dynamic Product Interaction:
		
//		Challenge: Do not click the first product. 
//		Instead, find the product named "Sauce Labs Backpack" 
//		specifically by its text and click its "Add to cart" button.
//		Find the product named "Sauce Labs Fleece Jacket" and click its "Add to cart" button.
//		Validation: Verify the Shopping Cart badge (top right) displays the number 2.
		
		driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']//following::button[1]")).click();
		driver.findElement(By.xpath("//*[text()='Sauce Labs Fleece Jacket']//following::button[1]")).click();
		String addcartitemnum = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
		System.out.println(addcartitemnum);
	
//		Step 3: Cart Management (List Handling):
//		Click on the Shopping Cart icon.
//		Validation: Use findElements to create a list of the items in the cart.
//		Verify that both products you added are present in the list.
//		Click the "Checkout" button.
		driver.findElement(By.className("shopping_cart_link")).click();
		List<WebElement> inventoryNameElement = driver.findElements(By.className("inventory_item_name"));
		for(WebElement element:inventoryNameElement) {
			System.out.println(element.getText());
		}
		driver.findElement(By.id("checkout")).click();
		
		
//		Step 4: Data Entry & Finish
//		Fill out the "Checkout: Your Information" form (First Name, Last Name, Zip Code).
//		Click "Continue."
//		Validation (The "Array" Practice): Extract the Item Total price and the Tax price from the summary.
//		Convert them to numbers in Java and verify that Total = Item Total + Tax.
//		Click "Finish."
		
		driver.findElement(By.id("first-name")).sendKeys("Mahesh");
		driver.findElement(By.id("last-name")).sendKeys("babu");
		driver.findElement(By.id("postal-code")).sendKeys("12345");
		driver.findElement(By.id("continue")).click();
		
		Thread.sleep(100);
		String subTotalText = driver.findElement(By.className("summary_subtotal_label")).getText();
		double subTotal =  Double.parseDouble((subTotalText.split("\\$"))[1]);
		
		String taxText = driver.findElement(By.className("summary_tax_label")).getText();
		double tax =  Double.parseDouble((taxText.split("\\$"))[1]);
		
		String totalPriceText = driver.findElement(By.className("summary_total_label")).getText();
		double totalPrice =  Double.parseDouble((totalPriceText.split("\\$"))[1]);
		System.out.println("Total Price:"+ totalPrice);
		System.out.println("Sub total:"+ subTotal);
		System.out.println("tax:"+ tax);
		double actualprice = subTotal+tax;
		if(Double.compare(totalPrice, actualprice)==0) {
			System.out.println("Price matched");
		}else {
			System.out.println("price not matched");
		}
		
		driver.findElement(By.xpath("//button[text()='Finish']")).click();
		
//		Step 5: Completion & Logout
		
//		Validation: Verify the header "Thank you for your order!" is displayed.
//		Click the "Burger Menu" (top left).
//		Synchronization Challenge: Use an Explicit Wait to wait for the "Logout" link 
//		to become visible (it has an animation), then click it.
//		Final Validation: Verify you are redirected back to the login page.
		
		String successHeaderText = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
		if(successHeaderText.equals("Thank you for your order!"))
			System.out.println("Header text matched");
		else
			System.out.println("Header text not mathed");
		
		driver.findElement(By.xpath("//div[@class='bm-burger-button']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[text()='Logout']")).click();
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.equals("https://www.saucedemo.com/"))
			System.out.println("Logout successful, User in homepage");
		else
			System.out.println("Logout unsuccessful");
		
		driver.quit();

	}

}
