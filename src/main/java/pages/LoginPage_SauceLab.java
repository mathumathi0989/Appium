package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage_SauceLab {

	
	private AppiumDriver driver;
	private WebDriverWait wait;
	private By uname = AppiumBy.accessibilityId("test-Username");
	private By pwd = AppiumBy.accessibilityId("test-Password");
	private By login_Btn = AppiumBy.accessibilityId("test-LOGIN");
	private By product_Text = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");
	public String actualText;
	
	public LoginPage_SauceLab (AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			}

	public String login (String username, String password){
		this.wait.until(ExpectedConditions.elementToBeClickable(uname)).sendKeys(username);
		this.wait.until(ExpectedConditions.elementToBeClickable(pwd)).sendKeys(password);
		this.wait.until(ExpectedConditions.elementToBeClickable(login_Btn)).click();
		actualText = this.wait.until(ExpectedConditions.elementToBeClickable(product_Text)).getText();
		return actualText;
		
	}
	
	
	
}
