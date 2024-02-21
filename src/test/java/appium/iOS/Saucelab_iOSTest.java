package appium.iOS;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import android_Pages.LoginPage_android_SauceLab;
import iOS_Pages.LoginPage_iOS_SauceLab;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class Saucelab_iOSTest {


	public  IOSDriver driver;
	public String ProductNameToAdd = "Sauce Labs Bike Light";
	
	@BeforeTest
	public void setup() {
		
		XCUITestOptions options = new XCUITestOptions ();
		 options.setApp ("/Users/mathumathibalakrishnan/eclipse/Mathumathi_2024/AndroidIos/src/test/resource/Builds/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.zip")
           .setDeviceName ("iPhone 15")
           .setPlatformName("IOS")
           .setPlatformVersion ("17.2")
           ;
		 

		 try {
	    	  
	           this.driver = new IOSDriver(new URL("http://localhost:4723/"), options);
	   
	        } catch (MalformedURLException e) {
            e.printStackTrace();
            
        }

        if (this.driver != null) {
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        }
		
		
	}
	
  

	@Test
    public void testSignUp () {
		LoginPage_iOS_SauceLab loginPage = new LoginPage_iOS_SauceLab(this.driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.actualText, "PRODUCTS");
      
    }
	
	@Test(dependsOnMethods = "testSignUp")
	public void ProdCheckout() throws Exception {
		
		LoginPage_iOS_SauceLab loginPage = new LoginPage_iOS_SauceLab(this.driver);    
		loginPage.addtoCart(ProductNameToAdd);
		loginPage.cart();
		Assert.assertEquals(loginPage.actualCartName, ProductNameToAdd);
		Assert.assertEquals(loginPage.actualCartPrice, loginPage.actualProdPrice);
		loginPage.checkout("Mathu", "Mathi", "12345");
		loginPage.overview();
		Assert.assertEquals(loginPage.finishName, ProductNameToAdd);
		Assert.assertEquals(loginPage.finishPrice, loginPage.actualProdPrice);
		loginPage.thankyou();
		Assert.assertEquals(loginPage.ActualThankyou, "THANK YOU FOR YOU ORDER");
		Assert.assertEquals(loginPage.ActualThankMessage, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
		Assert.assertEquals(loginPage.actualText, "PRODUCTS");
	}

	
	
    @AfterTest
	public void tearDown() {
		this.driver.quit ();
	}
    
    
}
