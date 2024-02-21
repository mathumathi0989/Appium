package iOS_Pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class LoginPage_iOS_SauceLab {


	
	private IOSDriver driver;
	private WebDriverWait wait;
	
	//login page
	private By uname = AppiumBy.accessibilityId("test-Username");
	private By pwd = AppiumBy.accessibilityId("test-Password");
	private By login_Btn = AppiumBy.accessibilityId("test-LOGIN");
	private By product_Text = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"PRODUCTS\"`]");
	public String actualText;
	
	//product page
		private By product_name;
		public void setProductName(String prod_name) {
	this.product_name = AppiumBy.iOSNsPredicateString("name == \"test-Item title\" AND label == \""+prod_name+ "\"");
		}
		private By prod_price = AppiumBy.accessibilityId("test-Price");
		public String actualProdPrice;
		private By addCart = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"test-ADD TO CART\"`]");
		private By cart = AppiumBy.iOSNsPredicateString("name == \"test-Cart\"");
		private By backToProd = AppiumBy.accessibilityId("test-BACK TO PRODUCTS");
		//**/XCUIElementTypeOther[`name == "1"`][4]
		
		
		//cart page
		public String actualCartName;
		public String actualCartPrice;
		private By cartName = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"test-Description\"`]/XCUIElementTypeStaticText[1]");
		private By cartPrice = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"test-Price\"`]/XCUIElementTypeStaticText");
		private By checkout_Btn = AppiumBy.accessibilityId("test-CHECKOUT");
		public String cartGest;

		//checkout page 
		private By fName = AppiumBy.accessibilityId("test-First Name");
		private By lName = AppiumBy.accessibilityId("test-Last Name");
		private By zip = AppiumBy.accessibilityId("test-Zip/Postal Code");
		private By continueBtn = AppiumBy.accessibilityId("test-CONTINUE");
		
		//Checkout overview page
		private By overviewName = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"test-Description\"`]/XCUIElementTypeStaticText[1]");
		private By overviewPrice = AppiumBy.iOSNsPredicateString("name == \"test-Price\"");
		public String finishName;
		public String finishPrice;
		private By itemTotal = AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@name ,\"Item total\")]");
		private By tax = AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@name ,\"Tax\")]");
		private By total = AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@name ,\"Total\")]");
		private By finish = AppiumBy.iOSNsPredicateString("name == \"test-FINISH\"");
		public String itemV;
		public String taxV;
		public String totalV;
		
		//Thank you page
		private By thankyou = AppiumBy.iOSNsPredicateString("name == \"THANK YOU FOR YOU ORDER\"");
		private By thankMessage = AppiumBy.iOSNsPredicateString("name == \"Your order has been dispatched, and will arrive just as fast as the pony can get there!\"");
		public String ActualThankyou;
		public String ActualThankMessage;
		private By Bk_Home = AppiumBy.iOSNsPredicateString("name == \"test-BACK HOME\"");
		
		private PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	
		
		public LoginPage_iOS_SauceLab(IOSDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			}

	public String login (String username, String password){
	//	this.wait.until(ExpectedConditions.elementToBeClickable(uname)).sendKeys(username);
		 WebElement userNameField = this.wait.until(ExpectedConditions.elementToBeClickable(uname));
		 userNameField.clear();
		 userNameField.sendKeys(username);
		 WebElement passwordField = this.wait.until(ExpectedConditions.elementToBeClickable(pwd));
	        passwordField.clear();
	        passwordField.sendKeys(password);
		this.wait.until(ExpectedConditions.elementToBeClickable(login_Btn)).click();
		actualText = this.wait.until(ExpectedConditions.elementToBeClickable(product_Text)).getText();
		return actualText;
		
	}
	
	public String addtoCart(String prod_name) throws Exception  {
		setProductName(prod_name);
		this.wait.until(ExpectedConditions.elementToBeClickable(product_name)).click();
		
		actualProdPrice = this.wait.until(ExpectedConditions.elementToBeClickable(prod_price)).getText();
		System.out.println(actualProdPrice);
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		//HashMap<String, Object> scrollObject = new HashMap();
	//scrollObject.put("predicateString", "name == 'test-ADD TO CART'");
//	this.driver.executeScript("mobile: scroll", scrollObject);
		
		
		this.wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();
	Thread.sleep(500); 
	
	//scrollObject.put("direction", "up");
	//js.executeScript("mobile: scroll", scrollObject);
		this.wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
		tap(358,55)	;
		this.wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
		Thread.sleep(500); 
		return actualProdPrice;
	}
	 

	public void tap(int x, int y) {
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

	public void cart() throws Exception {
		actualCartName = this.wait.until(ExpectedConditions.elementToBeClickable(cartName)).getText();
	//System.out.println(actualCartName);
		 actualCartPrice = this.wait.until(ExpectedConditions.elementToBeClickable(cartPrice)).getText();
		 Thread.sleep(500); 
	//	System.out.println(actualCartPrice);
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
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		
		 this.wait.until(ExpectedConditions.elementToBeClickable(finish)).click();; 
		//	this.driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"FINISH\"))")).click();;
		//Thread.sleep(700); 
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
