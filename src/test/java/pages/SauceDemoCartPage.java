package pages;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;

public class SauceDemoCartPage{

	WebDriver driver;
	ElementUtils elementUtils;
	
	public SauceDemoCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.elementUtils = new ElementUtils(driver);
	}
	

	
	@FindBy(className = "inventory_item_name")
	List<WebElement> inventoryNameElement;
	
	@FindBy(id="checkout")
	WebElement checkOutButton;
	
	@FindBy(id="first-name")
	WebElement firstNameTextField;
	
	@FindBy(id="last-name")
	WebElement lastNameTextField;
	
	@FindBy(id="postal-code")
	WebElement postalCodeTextField;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(className ="summary_subtotal_label")
	WebElement summarySubtotalText;
	
	@FindBy(className ="summary_tax_label")
	WebElement summaryTaxText;
	
	@FindBy(className ="summary_total_label")
	WebElement summaryTotalText;
	
	@FindBy(xpath="//button[text()='Finish']")
	WebElement finishButton;
	
	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement checkOutSuccessMessageTextElement;
	
	@FindBy(xpath="//div[@class='bm-burger-button']")
	WebElement burgerMenuButton;
	
	@FindBy(linkText = "Logout")
	WebElement logoutOption;
	
	
	
	public List<String> productsAddedToCart() {
		List<String> cartItems = new ArrayList<>();
		for(WebElement element:inventoryNameElement) {
			cartItems.add(element.getText());
		}
		return cartItems;
	}
	
	public void clickOnCheckOutButton() {
		checkOutButton.click();
	}
	
	public void enterFirstName(String firstName) {
		firstNameTextField.sendKeys(firstName);
	}
	public void enterLastName(String lastName) {
		lastNameTextField.sendKeys(lastName);
	}
	public void enterPostalCode(String postalCode) {
		postalCodeTextField.sendKeys(postalCode);
	}
	public void clickOnContinueButton() {
		continueButton.click();
		
	}
	
	public BigDecimal getSubTotalAmout() {
		return new BigDecimal(summarySubtotalText.getText().split("\\$")[1]);
	}
	
	public BigDecimal getTaxAmount() {
		return new BigDecimal(summaryTaxText.getText().split("\\$")[1]);
	}
	
	public BigDecimal getTotalAmount() {
		return new BigDecimal(summaryTotalText.getText().split("\\$")[1]);
	}
	
	public boolean comparePrice() {
		BigDecimal actualPrice = getSubTotalAmout().add(getTaxAmount());
		return actualPrice.compareTo(getTotalAmount()) == 0;
		
	}
	
	public void clickOnFinishButton() {
		finishButton.click();
	}
	
	public String getCheckOutSuccessConfiramationMessage() {
		return checkOutSuccessMessageTextElement.getText();
	}
	
	public void clickOnBurgerMenuIcon() {
		burgerMenuButton.click();
	}
	
	public void clickOnLogoutOption() {
		elementUtils.waitAndClickOnElement(logoutOption, 3);
	}
	
	
}
