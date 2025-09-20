package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.utils;

public class AccountRegistrationPage {
	 WebDriver driver; 
    // Constructor
    public AccountRegistrationPage(WebDriver driver) {
    	this.driver = driver;
	}
	    
    // Locators
    By txt_Firstname = By.xpath("//input[@id='input-firstname']");
    By txt_Lastname = By.xpath("//input[@id='input-lastname']");
    By txt_Email = By.xpath("//input[@id='input-email']");
    By txt_Telephone = By.xpath("//input[@id='input-telephone']");
    By txt_Password = By.xpath("//input[@id='input-password']");
	By txt_PasswordConfirm = By.xpath("//input[@id='input-confirm']");
    
	By cb_PrivacyPolicy = By.xpath("//input[@name='agree']");
	By msgConfirmation = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
	By btn_Continue = By.xpath("//input[@value='Continue']");
	
	 // Actions
	public void setFirstName(String fname) {
		utils.waitForVisibility(driver, txt_Firstname, 10);   // Wait until the element is visible. Can be used for all elements
        driver.findElement(txt_Firstname).sendKeys(fname);
    }
    public void setLastName(String lname) {
        //utils.waitForVisibility(driver, txt_Lastname, 10);
        driver.findElement(txt_Lastname).sendKeys(lname);
    }
    public void setEmail(String email) {
        //utils.waitForVisibility(driver, txt_Email, 10);
        driver.findElement(txt_Email).sendKeys(email);
    }
    public void setTelephone(String tel) {
        //utils.waitForVisibility(driver, txt_Telephone, 10);
        driver.findElement(txt_Telephone).sendKeys(tel);
    }
    public void setPassword(String pwd) {
        //utils.waitForVisibility(driver, txt_Password, 10);
        driver.findElement(txt_Password).sendKeys(pwd);
    }
    public void setConfirmPassword(String pwd) {
        //utils.waitForVisibility(driver, txt_PasswordConfirm, 10);
        driver.findElement(txt_PasswordConfirm).sendKeys(pwd);
    }
    public void clickPrivacyPolicy() {
        //utils.waitForVisibility(driver, cb_PrivacyPolicy, 10);
        driver.findElement(cb_PrivacyPolicy).click();
    }
    public void clickContinue() {
        //utils.waitForVisibility(driver, btn_Continue, 10);
        driver.findElement(btn_Continue).click();
    }
    public String getConfirmationMsg() {
	    try {
	        return driver.findElement(msgConfirmation).getText();
	    }
	    catch(Exception e) {
			return e.getMessage();
		}	
    }
}