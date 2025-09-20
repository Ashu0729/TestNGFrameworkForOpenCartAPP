package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.utils;

public class HomePage{
    WebDriver driver;
    
    // Constructor
    public HomePage(WebDriver driver) {
    this.driver = driver;
    }
    
    // Locators
    By lnk_MyAccount = By.xpath("//span[normalize-space()='My Account']");
    By lnk_Register = By.xpath("//a[normalize-space()='Register']");
    By lnk_Login = By.xpath("//a[normalize-space()='Login']");
    
    
    // Actions
    public void clickMyAccount() {
    	utils.waitForVisibility(driver, lnk_MyAccount, 10);   // Wait until the element is visible. Can be used for all elements
        driver.findElement(lnk_MyAccount).click();
    }
    public void clickRegister() {
        driver.findElement(lnk_Register).click();
    }
    
    public void clickLogin() {
        driver.findElement(lnk_Login).click();
    }
}