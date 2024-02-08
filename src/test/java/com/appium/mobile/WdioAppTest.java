package com.appium.mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.HttpCommandExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pages.signupPage_Wdio;

public class WdioAppTest {

	public  AndroidDriver driver;
	
	
	@BeforeTest
	public void setup() {
		
		UiAutomator2Options options = new UiAutomator2Options ();
		 options.setApp ("C:\\Users\\mathu\\eclipse-workspace\\mobile\\src\\test\\resource\\Builds\\android.wdio.native.app.v1.0.8.apk")
            .setDeviceName ("Mathu_Pixel 7 API 30")
            .setPlatformVersion ("11.0")
            .setAvd ("Mathu_Pixel_7_API_30")
            .setAppWaitActivity ("com.wdiodemoapp.MainActivity")
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
        signupPage_Wdio signUpPage = new signupPage_Wdio (this.driver);
        signUpPage.signup("admin@gmail.com", "admin1234");
        Assert.assertEquals(signUpPage.actualMessage, "You successfully signed up!");
        
        
        
    }

    @AfterTest
	public void tearDown() {
		this.driver.quit ();
	}
	
}
