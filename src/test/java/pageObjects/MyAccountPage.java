package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.utils;

public class MyAccountPage{
    WebDriver driver;
    
    // Constructor
    public MyAccountPage(WebDriver driver) {
    this.driver = driver;
    }
    
    // Locators
    By txt_MyAccount =By.xpath("//h2[normalize-space()='My Account']");
    By btn_Logout =By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");
    
    // Actions
    public String getConfirmationMsg() {
	    try {
	    	utils.waitForVisibility(driver, txt_MyAccount, 10);   // Wait until the element is visible. Can be used for all elements
	        return driver.findElement(txt_MyAccount).getText();
	    }
	    catch(Exception e) {
			return e.getMessage();
		}	
    }
    
    public boolean isConfirmationMsgExists() {    
    	try {
    		return driver.findElement(txt_MyAccount).isDisplayed();
		}
		catch(Exception e){
			return false;
		}
    }
    
    public void clickLogout() {
		driver.findElement(btn_Logout).click();
	}	
}