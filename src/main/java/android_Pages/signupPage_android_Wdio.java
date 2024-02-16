package android_Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class signupPage_android_Wdio {

	private AppiumDriver driver;
	private WebDriverWait wait;
	private By loginTab = AppiumBy.xpath("//android.widget.TextView[@text=\"Login\"]");
	private By signupTab = AppiumBy.xpath("//android.widget.TextView[@text=\"Sign up\"]");
	private By email = AppiumBy.accessibilityId("input-email");
	private By pwd = AppiumBy.accessibilityId("input-password");
	private By confirm_pwd = AppiumBy.accessibilityId("input-repeat-password");
	private By signup_Btn = AppiumBy.xpath("//android.widget.TextView[@text=\"SIGN UP\"]");
	private By signup_Message = AppiumBy.id("android:id/message");
	private By signup_AcceptBtn = AppiumBy.id("android:id/button1");
	public String actualMessage ;
	
	public signupPage_android_Wdio (AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			}

	public String signup (String username, String password){
	//	wait = new WebDriverWait(driver,30);
		this.wait.until(ExpectedConditions.elementToBeClickable(this.loginTab)).click();
		this.wait.until(ExpectedConditions.elementToBeClickable(this.signupTab)).click();
		this.wait.until(ExpectedConditions.elementToBeClickable(email)).sendKeys(username);
		this.wait.until(ExpectedConditions.elementToBeClickable(pwd)).sendKeys(password);
		this.wait.until(ExpectedConditions.elementToBeClickable(confirm_pwd)).sendKeys(password);
		this.wait.until(ExpectedConditions.elementToBeClickable(signup_Btn)).click();
		actualMessage = this.wait.until(ExpectedConditions.elementToBeClickable(signup_Message)).getText();
		this.wait.until(ExpectedConditions.elementToBeClickable(signup_AcceptBtn)).click();
		return actualMessage;
	}
	
	
	
}
