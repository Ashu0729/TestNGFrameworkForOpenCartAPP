package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

public class TC002_LoginTest extends BasePage {
	
  @Test(groups = {"Sanity","Master"})
  public void verify_login() {
	System.out.println("**Starting TC002_LoginTest**");
	
	try {
		HomePage hp = new HomePage(driver);
		
		logger.info("clicking my account link");
		hp.clickMyAccount();

		logger.info("clicking my Login link");
		hp.clickLogin();
		
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		
		MyAccountPage accountPage = new MyAccountPage(driver);
		String confmsg = accountPage.getConfirmationMsg();
		
		Assert.assertEquals(confmsg, "My Account", "Confirmation message mismatch");
		
		logger.info("Test passed");
	}
	catch(Exception e) {
		logger.info("Failed TC002_LoginTest");
		logger.error("Test failed: " + e.getMessage());
		logger.debug("Test failed");
		
		Assert.fail("Test failed: " + e.getMessage());
	
	}
	finally {
		logger.info("**Finished TC002_LoginTest**");
	}
  }
}
