package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.utils;

public class LoginPage{
    WebDriver driver;
    
    // Constructor
    public LoginPage(WebDriver driver) {
    this.driver = driver;
    }
    
    // Locators
    By txt_Email =By.xpath("//input[@id='input-email']");
    By txt_Pwd =By.xpath("//input[@id='input-password']");
    By btn_Login = By.xpath("//input[@value='Login']");
    
    
    // Actions
    public void setEmail(String email) {
    	utils.waitForVisibility(driver, txt_Email, 10);   // Wait until the element is visible. Can be used for all elements
        driver.findElement(txt_Email).sendKeys(email);
    }
    
    public void setPassword(String password) {
        driver.findElement(txt_Pwd).sendKeys(password);
    }
    
    public void clickLogin() {
        driver.findElement(btn_Login).click();
    }
}