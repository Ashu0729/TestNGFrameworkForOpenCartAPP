package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.BasePage;
import pageObjects.HomePage;
import utilities.utils;

public class TC001_AccountRegistrationTest extends BasePage {
	
  @Test(groups = {"Regression","Master"})
  public void verify_account_registration() {
	System.out.println("**Starting TC001_AccountRegistrationTest**");
	
	try {
		HomePage hp = new HomePage(driver);
		
		logger.info("clicking my account link");
		hp.clickMyAccount();

		logger.info("clicking my register link");
		hp.clickRegister();
		  
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details...");
		regpage.setFirstName(utils.randomString());
		regpage.setLastName(utils.randomString());
		regpage.setEmail(utils.randomString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(utils.randomNumber());
			
		String password=utils.randomAlphaNumberic();
			
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickPrivacyPolicy();
		regpage.clickContinue();
		
		System.out.println("Validating expected message..");
		logger.info("Validating expected message..");
		
		String confmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");
		
		logger.info("Test passed");
	}
	catch(Exception e) {
		logger.info("Failed TC001_AccountRegistrationTest");
		logger.error("Test failed: " + e.getMessage());
		logger.debug("Test failed");
		
		Assert.fail("Test failed: " + e.getMessage());
	
	}
	finally {
		logger.info("**Finished TC001_AccountRegistrationTest**");
	}
  }
}
