package iOS_Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class signupPage_iOS_Wdio {

	
	private IOSDriver driver;
	private WebDriverWait wait;
	private By loginTab = AppiumBy.accessibilityId("Login");
	private By signupTab = AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Sign up\"]");
	private By email = AppiumBy.accessibilityId("input-email");
	private By pwd = AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`name == \"input-password\"`]");
	private By confirm_pwd = AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`name == \"input-repeat-password\"`]");
	private By signup_Btn = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"SIGN UP\"`]");
	private By signup_Message = AppiumBy.accessibilityId("You successfully signed up!");
	private By signup_AcceptBtn = AppiumBy.accessibilityId("OK");
	public String actualMessage ;
	
	public signupPage_iOS_Wdio (IOSDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			}

	public String signup (String username, String password){
	//	wait = new WebDriverWait(driver,30);
		this.wait.until(ExpectedConditions.elementToBeClickable(this.loginTab)).click();
		this.wait.until(ExpectedConditions.elementToBeClickable(this.signupTab)).click();
		this.wait.until(ExpectedConditions.elementToBeClickable(email)).sendKeys(username);
		 WebElement passwordField = this.wait.until(ExpectedConditions.elementToBeClickable(pwd));
	        passwordField.clear();
	        passwordField.sendKeys(password);
		this.wait.until(ExpectedConditions.elementToBeClickable(confirm_pwd)).clear();
		this.wait.until(ExpectedConditions.elementToBeClickable(confirm_pwd)).sendKeys(password);
		this.wait.until(ExpectedConditions.elementToBeClickable(signup_Btn)).click();
		actualMessage = this.wait.until(ExpectedConditions.elementToBeClickable(signup_Message)).getText();
		this.wait.until(ExpectedConditions.elementToBeClickable(signup_AcceptBtn)).click();
		return actualMessage;
	}
	
	
	
}
