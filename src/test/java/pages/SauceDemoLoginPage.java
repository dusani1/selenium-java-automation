package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoLoginPage {

	public WebDriver driver;
	
	public SauceDemoLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="user-name") 
	WebElement usernameTextField;
	
	@FindBy(name="password")
	WebElement passwordField;
	
	@FindBy(css = "#login-button")
	WebElement loginButton;
	
	
	
	public void enterUserName(String username) {
		usernameTextField.sendKeys(username);
		
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
}
