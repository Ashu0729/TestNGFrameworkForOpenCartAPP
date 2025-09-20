package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utilities.dataProvider;

public class TC003_LoginTestDataDriven extends BasePage {
	
  @Test(dataProvider="LoginData", dataProviderClass=dataProvider.class, groups = "Datadriven")
  public void verify_loginTDD(String sc, String email, String password, String exp) {
	System.out.println("**Starting TC003_LoginTestDataDriver**");
	
	try {
		HomePage hp = new HomePage(driver);
		
		logger.info("clicking my account link");
		hp.clickMyAccount();

		logger.info("clicking my Login link");
		hp.clickLogin();
		
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage accountPage = new MyAccountPage(driver);
		boolean expected = Boolean.parseBoolean(exp);
		
		if (expected) {
			Assert.assertEquals(accountPage.getConfirmationMsg(), "My Account", "Test Failed : Confirmation message mismatch");	
			logger.info("Test passed : Login Successful");
			
			accountPage.clickLogout();
		}
		else {
			Assert.assertFalse(accountPage.isConfirmationMsgExists(), "Test Failed : Login should not be successful");
			logger.info("Test passed : Login should not be successful");	
		}
			
		}

	catch(Exception e) {
		logger.info("Failed TC003_LoginTestDataDriver");
		logger.error("Test failed: " + e.getMessage());
		logger.debug("Test failed");
		
		Assert.fail("Test failed: " + e.getMessage());	
	}
	
	finally {
		logger.info("**Finished TC003_LoginTestDataDriver**");
	}
  }
}