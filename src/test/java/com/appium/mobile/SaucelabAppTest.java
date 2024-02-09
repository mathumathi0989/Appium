package com.appium.mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pages.LoginPage_SauceLab;
import pages.signupPage_Wdio;

public class SaucelabAppTest {

	public  AndroidDriver driver;
	public String ProductNameToAdd = "Sauce Labs Bike Light";
	
	@BeforeTest
	public void setup() {
		
		UiAutomator2Options options = new UiAutomator2Options ();
		 options.setApp ("C:\\Users\\mathu\\eclipse-workspace\\mobile\\src\\test\\resource\\Builds\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk")
            .setDeviceName ("Mathu_Pixel 7 API 30")
            .setPlatformVersion ("11.0")
            .setAvd ("Mathu_Pixel_7_API_30")
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
        LoginPage_SauceLab loginPage = new LoginPage_SauceLab (this.driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.actualText, "PRODUCTS");
      
    }
	
	@Test(dependsOnMethods = "testSignUp")
	public void ProdCheckout() {
		
		   LoginPage_SauceLab loginPage = new LoginPage_SauceLab (this.driver);    
		loginPage.addtoCart(ProductNameToAdd);
		loginPage.cart();
		Assert.assertEquals(loginPage.actualCartName, ProductNameToAdd);
		Assert.assertEquals(loginPage.actualCartPrice, loginPage.actualProdPrice);
	}

    @AfterTest
	public void tearDown() {
		this.driver.quit ();
	}
    
}
