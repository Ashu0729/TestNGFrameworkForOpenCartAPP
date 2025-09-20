package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class utils {
   // Wait for element to be visible
	public static void waitForVisibility(WebDriver driver, By locator, int timeout) {
       new WebDriverWait(driver, Duration.ofSeconds(timeout))
           .until(ExpectedConditions.visibilityOfElementLocated(locator));
   }
   
	public static String randomString(){
		String generatedstring=RandomStringUtils.random(7, true, false);
		return generatedstring;
	}
	
	public static String randomNumber(){
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public static String randomAlphaNumberic(){
		String generatedstring=RandomStringUtils.random(3, true, false);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	
	
}