package appium.android;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import android_Pages.LoginPage_android_SauceLab;
import android_Pages.signupPage_android_Wdio;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Saucelab_AndroidTest {

	public  AndroidDriver driver;
	public String ProductNameToAdd = "Sauce Labs Bike Light";
	
	@BeforeTest
	public void setup() {
		
		UiAutomator2Options options = new UiAutomator2Options ();
		 options.setApp ("/Users/mathumathibalakrishnan/eclipse/Mathumathi_2024/AndroidIos/src/test/resource/Builds/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk")
            .setDeviceName ("Mathu_Pixel 7 API 33")
            .setPlatformVersion ("13.0")
            .setAvd ("Mathu_Pixel_7_API_33")
            .setAppWaitActivity ("com.swaglabsmobileapp.MainActivity")
           // .setCapability ("appium:automationName", "UiAutomator2");
            .setCapability ("appium:settings[ignoreUnimportantViews]", true);
		 

      try {
    	  
           this.driver = new AndroidDriver(new URL("http://localhost:4723/"), options);
   
        } catch (MalformedURLException e) {
            e.printStackTrace();
            
        }

        if (this.driver != null) {
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        }
		
		
	}
	
    private void setCapability(String string, boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testSignUp () {
        LoginPage_android_SauceLab loginPage = new LoginPage_android_SauceLab (this.driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.actualText, "PRODUCTS");
      
    }
	
	@Test(dependsOnMethods = "testSignUp")
	public void ProdCheckout() throws Exception {
		
		   LoginPage_android_SauceLab loginPage = new LoginPage_android_SauceLab (this.driver);    
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
