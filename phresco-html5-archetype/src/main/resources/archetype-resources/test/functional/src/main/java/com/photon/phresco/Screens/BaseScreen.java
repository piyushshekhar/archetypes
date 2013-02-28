package com.photon.phresco.Screens;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoUiConstants;

public class BaseScreen {

	private WebDriver driver = null;
	private ChromeDriverService chromeService = null;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element = null;
	private PhrescoUiConstants phrescoUiConstants = null;
	private String resolution = null;
	DesiredCapabilities capabilities;

	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser,String selectedPlatform, String applicationURL,
			String applicatinContext, PhrescoUiConstants phrescoUiConstants)
					 throws AWTException, IOException, ScreenActionFailedException {

		this.phrescoUiConstants = phrescoUiConstants;
		try {
			instantiateBrowser(selectedBrowser,selectedPlatform, applicationURL, applicatinContext);
		} catch (ScreenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void instantiateBrowser(String selectedBrowser,String selectedPlatform,
			String applicationURL, String applicationContext)
					 throws ScreenException,
						MalformedURLException  {

		URL server = new URL("http://localhost:4444/wd/hub/");
		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			log.info("-------------***LAUNCHING GOOGLECHROME***--------------");
			try {

				/*
				 * chromeService = new ChromeDriverService.Builder()
				 * .usingChromeDriverExecutable( new File(getChromeLocation()))
				 * .usingAnyFreePort().build(); log.info(
				 * "-------------***LAUNCHING GOOGLECHROME***--------------");
				 * chromeService.start();
				 */
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
				/*
				 * break; capabilities.setPlatform(Platform)
				 * capabilities.setPlatform(selectedPlatform); driver = new
				 * RemoteWebDriver(server, capabilities);
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("---------------***LAUNCHING INTERNET EXPLORE***-----------");
			try {
				capabilities = new DesiredCapabilities();
				capabilities.setJavascriptEnabled(true);
				capabilities.setBrowserName("iexplorer");
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
			else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
				log.info("-------------***LAUNCHING OPERA***--------------");
				try {
					
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("opera");
				capabilities.setCapability("opera.autostart ",true);

				System.out.println("-----------checking the OPERA-------");
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		} 
			else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_SAFARI)) {
				log.info("-------------***LAUNCHING SAFARI***--------------");
				try {
					
			    capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("safari");
				capabilities.setCapability("safari.autostart ", true);
				System.out.println("-----------checking the SAFARI-------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("-------------***LAUNCHING FIREFOX***--------------");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			System.out.println("-----------checking the firefox-------");
			// break;
			// driver = new RemoteWebDriver(server, capabilities);

		} else {
			throw new ScreenException(
					"------Only FireFox,InternetExplore and Chrome works-----------");
		}

		/**
		 * These 3 steps common for all the browsers
		 */

		/* for(int i=0;i<platform.length;i++) */

		if (selectedPlatform.equalsIgnoreCase("WINDOWS")) {
			capabilities.setCapability(CapabilityType.PLATFORM,
					Platform.WINDOWS);
			// break;
		} else if (selectedPlatform.equalsIgnoreCase("LINUX")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
			// break;
		} else if (selectedPlatform.equalsIgnoreCase("MAC")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.MAC);
			// break;
		}
		driver = new RemoteWebDriver(server, capabilities);
        setBrowserResolution();
		driver.get(applicationURL + applicationContext);
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public void setBrowserResolution() {
		resolution = this.phrescoUiConstants.RESOLUTION;
		if (resolution != null) {
			String[] tokens = resolution.split("x");
			String resolutionX = tokens[0];
			String resolutionY = tokens[1];
			int Xpath = Integer.parseInt(resolutionX);
			int Ypath = Integer.parseInt(resolutionY);
			Dimension screenResolution = new Dimension(Xpath, Ypath);
			driver.manage().window().setSize(screenResolution);
		} else {
			driver.manage().window().maximize();
		}
	}

	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");
		if (driver != null) {
			driver.quit();
			if (chromeService != null) {

			}
		}

	}

	public String getChromeLocation() {
		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();
		String location = directory + targetDirectory;
		return location;
	}

	public String getChromeFile() {
		if (System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)) {
			log.info("*******WINDOWS MACHINE FOUND*************");
			// getChromeLocation("/chromedriver.exe");
			return Constants.WINDOWS_DIRECTORY + "/chromedriver.exe";
		} else if (System.getProperty("os.name").startsWith(Constants.LINUX_OS)) {
			log.info("*******LINUX MACHINE FOUND*************");
			return Constants.LINUX_DIRECTORY_64 + "/chromedriver";
		} else if (System.getProperty("os.name").startsWith(Constants.MAC_OS)) {
			log.info("*******MAC MACHINE FOUND*************");
			return Constants.MAC_DIRECTORY + "/chromedriver";
		} else {
			throw new NullPointerException("******PLATFORM NOT FOUND********");
		}

	}

	public void getXpathWebElement(String xpath) throws Exception {
		log.info("Entering:-----getXpathWebElement-------");
		try {

			element = driver.findElement(By.xpath(xpath));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getXpathWebElement()-----------");
			t.printStackTrace();

		}

	}

	public void getIdWebElement(String id) throws ScreenException {
		log.info("Entering:---getIdWebElement-----");
		try {
			element = driver.findElement(By.id(id));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getIdWebElement()----------");
			t.printStackTrace();

		}

	}

	public void getcssWebElement(String selector) throws ScreenException {
		log.info("Entering:----------getIdWebElement----------");
		try {
			element = driver.findElement(By.cssSelector(selector));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getIdWebElement()--------");

			t.printStackTrace();

		}

	}

	public void waitForElementPresent(String locator, String methodName)
			throws IOException, Exception {
		try {
			log.info("Entering:--------waitForElementPresent()--------");
			By by = By.xpath(locator);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			log.info("Waiting:--------One second----------");
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
			/*File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(GetCurrentDir.getCurrentDirectory() + "\\"
							+ methodName + ".png"));
			throw new RuntimeException("waitForElementPresent"
					+ super.getClass().getSimpleName() + " failed", e);*/
			Assert.assertNull(e);

		}
	}
	public void isTextPresent(String text) {
		if (text!= null){
		boolean value=driver.findElement(By.tagName("body")).getText().contains(text);	
		Assert.assertTrue(value);   
	    
	    }
		else
		{
			throw new RuntimeException("---- Text not existed----");
		}
	    
	    
	    
	}	
	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("Entering:------presenceOfElementLocated()-----Start");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				log.info("Entering:*********presenceOfElementLocated()******End");
				return driver.findElement(locator);

			}

		};

	}
	
	// test methods
	public void testHellow_world_text(String methodName) throws Exception {

		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		}
		log.info("Entering:******Hellow world test*********");
		isTextPresent(Constants.HELLOWORLD_TEXT_MSG);
		
		
    }
	
    public void testFailureCase(String methodName) throws Exception {

		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		}
		log.info("Entering:******Hellow world test*********");
		isTextPresent(Constants.WRONG_TEXT_MSG);
		
		
    }
	
	
	
	
	public void click() throws ScreenException {
		log.info("Entering:********click operation start********");
		try {
			element.click();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********click operation end********");

	}

	public void clear() throws ScreenException {
		log.info("Entering:********clear operation start********");
		try {
			element.clear();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********clear operation end********");

	}

	public void sendKeys(String text) throws ScreenException {
		log.info("Entering:********enterText operation start********");
		try {
			clear();
			element.sendKeys(text);

		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********enterText operation end********");
	}

	public void submit() throws ScreenException {
		log.info("Entering:********submit operation start********");
		try {
			element.submit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********submit operation end********");

	}

}
