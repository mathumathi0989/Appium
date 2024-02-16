package appium.iOS;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import android_Pages.signupPage_android_Wdio;
import iOS_Pages.signupPage_iOS_Wdio;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class wdioApp_iOSTest {

	

	public  IOSDriver driver;
	
	
	@BeforeTest
	public void setup() {
		
		XCUITestOptions options = new XCUITestOptions ();
		 options.setApp ("/Users/mathumathibalakrishnan/eclipse/Mathumathi_2024/AndroidIos/src/test/resource/Builds/ios.simulator.wdio.native.app.v1.0.8.zip")
            .setDeviceName ("iPhone 15")
            .setPlatformName("IOS")
            .setPlatformVersion ("17.2")
           // .setCapability("appium.automationName", "XCUITest")
           // .setCapability ("appium:automationName", "UiAutomator2");
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
        signupPage_iOS_Wdio signUpPage = new signupPage_iOS_Wdio (this.driver);
        signUpPage.signup("admin@gmail.com", "bbdmin1234");
        Assert.assertEquals(signUpPage.actualMessage, "You successfully signed up!");
        
        
        
    }

    @AfterTest
	public void tearDown() {
		this.driver.quit ();
	}
    
    
}
