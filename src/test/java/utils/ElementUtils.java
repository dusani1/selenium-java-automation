package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	
	WebDriver driver;

	public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }
	
	public void clickOnElement(WebElement element) {
		element.click();
	}
	
	public void waitAndClickOnElement(WebElement element, int seconds) {
		waitForElementToVisible(element, seconds).click();
	}
	
	
	public WebElement waitForElementToVisible(WebElement element, int seconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
	}
	
}
