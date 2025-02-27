package android_Pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class LoginPage_android_SauceLab {

	
	private AppiumDriver driver;
	private WebDriverWait wait;
	
	//login page
	private By uname = AppiumBy.accessibilityId("test-Username");
	private By pwd = AppiumBy.accessibilityId("test-Password");
	private By login_Btn = AppiumBy.accessibilityId("test-LOGIN");
	private By product_Text = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");
	public String actualText;
	
	//product page
		private By product_name;
		public void setProductName(String prod_name) {
	this.product_name = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + prod_name + "\")");
		}
		private By prod_price = AppiumBy.accessibilityId("test-Price");
		public String actualProdPrice;
		private By addCart = AppiumBy.xpath("//android.widget.TextView[@text=\"ADD TO CART\"]");
		private By cart = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup");
		
		//cart page
		public String actualCartName;
		public String actualCartPrice;
		private By cartName = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
		private By cartPrice = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
		private By checkout_Btn = AppiumBy.accessibilityId("test-CHECKOUT");
		public String cartGest;

		//checkout page 
		private By fName = AppiumBy.accessibilityId("test-First Name");
		private By lName = AppiumBy.accessibilityId("test-Last Name");
		private By zip = AppiumBy.accessibilityId("test-Zip/Postal Code");
		private By continueBtn = AppiumBy.xpath("//android.widget.TextView[@text=\"CONTINUE\"]");
		
		//Checkout overview page
		private By overviewName = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
		private By overviewPrice = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView[1]");
		public String finishName;
		public String finishPrice;
		private By itemTotal = AppiumBy.xpath("//android.widget.TextView[contains(@text,\"Item\")]");
		private By tax = AppiumBy.xpath("//android.widget.TextView[contains(@text,\"Tax\")]");
		private By total = AppiumBy.xpath("//android.widget.TextView[contains(@text,\"Total\")]");
		private By finish = AppiumBy.xpath("//android.widget.TextView[@text=\"FINISH\"]");
		public String itemV;
		public String taxV;
		public String totalV;
		
		//Thank you page
		private By thankyou = AppiumBy.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");
		private By thankMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Your order has been dispatched, and will arrive just as fast as the pony can get there!\"]");
		public String ActualThankyou;
		public String ActualThankMessage;
		private By Bk_Home = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-BACK HOME\"]");
		
		
		
		
		public LoginPage_android_SauceLab (AndroidDriver driver) {
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
	
	public String addtoCart(String prod_name) throws Exception  {
		setProductName(prod_name);
		this.wait.until(ExpectedConditions.elementToBeClickable(product_name)).click();
		WebElement eleD = this.driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains(\"test-ADD TO CART\"))"));
		eleD.click();
		Thread.sleep(500); 
	actualProdPrice = this.wait.until(ExpectedConditions.elementToBeClickable(prod_price)).getText();
	//	System.out.println(actualProdPrice);
		this.wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
		return actualProdPrice;
	}
	 

	public void cart() {
		actualCartName = this.wait.until(ExpectedConditions.elementToBeClickable(cartName)).getText();
	//	System.out.println(actualCartName);
		 actualCartPrice = this.wait.until(ExpectedConditions.elementToBeClickable(cartPrice)).getText();
		// System.out.println(actualCartPrice);
		 this.wait.until(ExpectedConditions.elementToBeClickable(checkout_Btn)).click();
	}
	
	public void checkout(String FirstName, String LastName, String zipCode) {
		 this.wait.until(ExpectedConditions.elementToBeClickable(fName)).sendKeys(FirstName);
		 this.wait.until(ExpectedConditions.elementToBeClickable(lName)).sendKeys(LastName);
		 this.wait.until(ExpectedConditions.elementToBeClickable(zip)).sendKeys(zipCode);
		  this.wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();; 
	}
	
	public void overview() throws Exception {
		finishName = this.wait.until(ExpectedConditions.elementToBeClickable(overviewName)).getText();
		
		finishPrice = this.wait.until(ExpectedConditions.elementToBeClickable(overviewPrice)).getText();
		
		
		itemV = this.wait.until(ExpectedConditions.elementToBeClickable(itemTotal)).getText();
		System.out.println(itemV);
		
		taxV = this.wait.until(ExpectedConditions.elementToBeClickable(tax)).getText();
		System.out.println(taxV);
		
			totalV = this.wait.until(ExpectedConditions.elementToBeClickable(total)).getText();
		System.out.println(totalV);
		
			this.driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"FINISH\"))")).click();;
		Thread.sleep(700); 
	}
	
	public void thankyou() {
		ActualThankyou = this.wait.until(ExpectedConditions.elementToBeClickable(thankyou)).getText();
		System.out.println(ActualThankyou);
		ActualThankMessage = this.wait.until(ExpectedConditions.elementToBeClickable(thankMessage)).getText();
		System.out.println(ActualThankMessage);
		this.wait.until(ExpectedConditions.elementToBeClickable(Bk_Home)).click();;
		actualText = this.wait.until(ExpectedConditions.elementToBeClickable(product_Text)).getText();
	}
	
}
