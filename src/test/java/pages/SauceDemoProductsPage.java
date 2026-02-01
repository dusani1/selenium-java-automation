package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoProductsPage {
	WebDriver driver;
	
	public SauceDemoProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}

	@FindBy(xpath="//span[@class='shopping_cart_badge']")
	WebElement addcartitemnumElement;
	
	@FindBy(className = "shopping_cart_link")
	WebElement shopingCartIcon;
	
	public int noOfItemsAddedInCart() {
		return Integer.parseInt(addcartitemnumElement.getText());
	}
	
	public void addProductToCart(String[] items) {
		for (String item : items) {
			String dynamicItemXpath = "//*[text()='" + item + "']//following::button[1]";
			driver.findElement(By.xpath(dynamicItemXpath)).click();
		}
	}
	
	public void clickOnShopingCartIcon() {
		shopingCartIcon.click();
	}
	
	
	
	
}
