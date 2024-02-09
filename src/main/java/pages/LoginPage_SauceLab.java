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
		private By addCart = AppiumBy.accessibilityId("test-ADD TO CART");
		private By cart = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup");
		//cart page
		public String actualCartName;
		public String actualCartPrice;
		private By cartName = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
		private By cartPrice = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
		private By checkout_Btn = AppiumBy.accessibilityId("test-CHECKOUT");

   // AppiumBy.accessibilityId("test-CONTINUE");
	
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
	
	public String addtoCart(String prod_name) {
		setProductName(prod_name);
		this.wait.until(ExpectedConditions.elementToBeClickable(product_name)).click();
		actualProdPrice = this.wait.until(ExpectedConditions.elementToBeClickable(prod_price)).getText();
		this.wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();
		this.wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
		return actualProdPrice;
	}
	
	public void cart() {
		actualCartName = this.wait.until(ExpectedConditions.elementToBeClickable(cartName)).getText();
		 actualCartPrice = this.wait.until(ExpectedConditions.elementToBeClickable(cartPrice)).getText();
		 this.wait.until(ExpectedConditions.elementToBeClickable(checkout_Btn)).click();
	}
	
}
