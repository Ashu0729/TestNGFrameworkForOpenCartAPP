package pageObjects;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BasePage {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Global setup before suite");
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void beforeClass(String os, String browser) throws IOException {
    	//loading Properties file to Read configurations
    	FileReader file = new FileReader("./src//test//resources//config.properties");
    	p = new Properties();
    	p.load(file);
    	
    	//Log4hj2 configuration
    	logger = LogManager.getLogger(this.getClass());
    	

    	logger.info("****** Setup before class with Browser: " + browser+" and OS:  "+os+" ******");
    	
    	//Decide Remote or Local execution
    	if (p.getProperty("executionEnv").equalsIgnoreCase("remote")) {
            logger.info("Remote Execution on Selenium Grid");
            try {
                String gridUrl = p.getProperty("gridURL");
                String platformName;
                switch (os.toLowerCase()) {
                    case "windows": platformName = "Windows"; break;
                    case "mac": platformName = "MAC"; break;
                    case "linux": platformName = "LINUX"; break;
                    default: logger.error("Pls provide correct OS"); return;
                }
                logger.info("Grid URL: " + gridUrl);
                logger.info("Platform: " + platformName);
                logger.info("Browser: " + browser);
                switch (browser.toLowerCase()) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setPlatformName(platformName);
                        driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                        break;
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.setPlatformName(platformName);
                        driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                        break;
                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.setPlatformName(platformName);
                        driver = new RemoteWebDriver(new URL(gridUrl), edgeOptions);
                        break;
                    default:
                        logger.error("Pls provide correct Browser");
                        return;
                }
            } catch (Exception e) {
                logger.error("Remote WebDriver setup failed", e);
                e.printStackTrace();
                throw new RuntimeException("Remote WebDriver setup failed", e);
            }
        }
        if (p.getProperty("executionEnv").equalsIgnoreCase("local")) {
    		// Browser selection
    		switch (browser.toLowerCase()) {
    	         case "chrome": driver = new ChromeDriver(); break;
    	         case "firefox": driver = new FirefoxDriver(); break;
    	         case "edge": driver = new EdgeDriver(); break;
    	         default: throw new IllegalArgumentException("Unsupported browser: " + browser);
        	 }
    	}
        // Always navigate to app URL after driver creation
        driver.get(p.getProperty("appURL"));
        logger.info("Navigated to URL: " + driver.getCurrentUrl());
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));         
    }

    @BeforeMethod
    public void beforeMethod() {
    	logger.info("Setup before each test method");
    }

    @AfterMethod
    public void afterMethod() {
    	logger.info("Cleanup after each test method");
    }
    
    @AfterClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void afterClass(String os, String browser) {
    	logger.info("****** Teardown/closing after class for Browser: " + browser+" ******");
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
    	logger.info("Global teardown after suite");
    }
    
    
    // Capture screenshot method
    public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
    
}