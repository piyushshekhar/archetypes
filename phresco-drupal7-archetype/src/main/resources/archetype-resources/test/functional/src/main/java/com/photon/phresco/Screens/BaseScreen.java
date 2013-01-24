package com.photon.phresco.Screens;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
import com.photon.phresco.uiconstants.DrupalData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;



public class BaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private  Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;
	private DrupalData phpConstants;
	private UIConstants uiConstants;
	private UserInfoConstants userInfo;
	private  PhrescoUiConstants phrsc;
	DesiredCapabilities capabilities;
	


	public BaseScreen() {

	}
	
	/**
	 * Invoking the super class method through passing the vale of Browser,URL, Context, then PhpData,UIConstants,UserInfoConstants Xml Values
	 * Then triggering instantiateBrowser
	 * @throws ScreenException
	 */
	public BaseScreen(String selectedBrowser,String selectedPlatform , String applicationURL,
			String applicationContext, DrupalData phpConstants,
			UIConstants uiConstants, UserInfoConstants userInfo)  throws AWTException, IOException, ScreenActionFailedException {

		this.phpConstants = phpConstants;
		this.uiConstants = uiConstants;
		this.userInfo=userInfo;
		try {
			instantiateBrowser(selectedBrowser, selectedPlatform, applicationURL, applicationContext);
		} catch (ScreenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void instantiateBrowser(String selectedBrowser,String selectedPlatform, 
			String applicationURL, String applicationContext)
					 throws ScreenException,
						MalformedURLException {

		URL server = new URL("http://localhost:4444/wd/hub/");
		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			log.info("-------------***LAUNCHING GOOGLECHROME***--------------");
			try {
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("---------------***LAUNCHING INTERNET EXPLORE***-----------");
			driver = new InternetExplorerDriver();
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("iexplore");
			// break;
			// capabilities.setPlatform(selectedPlatform);

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
		driver.get(applicationURL + applicationContext);
		

	}
	

	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");
		if (driver != null) {

			driver.quit();
		}
		if (chromeService != null) {
			chromeService.stop();
		}
	}

	public  String getChromeLocation() {

		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();
		String location = directory + targetDirectory;
		return location;
	}

	public  String getChromeFile() {
		if (System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)) {
			log.info("*******WINDOWS MACHINE FOUND*************");

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

	public WebElement getXpathWebElement(String xpath) throws Exception {
		log.info("Entering:-----getXpathWebElement-------");
		try {

			element = driver.findElement(By.xpath(xpath));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getXpathWebElement()-----------");
			t.printStackTrace();

		}
		return element;
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
			WebDriverWait wait = new WebDriverWait(driver, 0);
			log.info("Waiting:--------One second----------");
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
			Assert.assertNull(e);
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
	
	public void createAccount(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
        
		} 
		waitForElementPresent(this.uiConstants.CREATENEWACC, methodName);
		getXpathWebElement(this.uiConstants.CREATENEWACC);
		click();
		Thread.sleep(3000);
		waitForElementPresent(this.uiConstants.NAME, methodName);
		getXpathWebElement(this.uiConstants.NAME);
		sendKeys(this.userInfo.NAME);
		waitForElementPresent(this.uiConstants.EMAIL, methodName);
		getXpathWebElement(this.uiConstants.EMAIL);
		sendKeys(this.userInfo.EMAIL);
		waitForElementPresent(this.uiConstants.SUBMIT, methodName);
		getXpathWebElement(this.uiConstants.SUBMIT);
		click();
		waitForElementPresent(this.phpConstants.TEXTVALUE, methodName);
		isTextPresent(phpConstants.TEXTVALUE);
		Thread.sleep(5000);
	}
	
	
	public void loginDrupal(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
        
		} 
		
		waitForElementPresent(this.uiConstants.LOGIN, methodName);
		getXpathWebElement(uiConstants.LOGIN);
		click();
		Thread.sleep(3000);
		waitForElementPresent(this.uiConstants.NAME, methodName);
		getXpathWebElement(this.uiConstants.NAME);
		sendKeys(this.userInfo.NAME);
		waitForElementPresent(this.uiConstants.LOGINPASSWORD, methodName);
		getXpathWebElement(this.uiConstants.LOGINPASSWORD);
		sendKeys(this.userInfo.LOGINPASSWORD);
		waitForElementPresent(this.uiConstants.SUBMIT, methodName);
		getXpathWebElement(this.uiConstants.SUBMIT);
		click();
		Thread.sleep(3000);
		waitForElementPresent(this.uiConstants.LOGOUT, methodName);
		getXpathWebElement(this.uiConstants.LOGOUT);
		click();
		Thread.sleep(3000);
		
		
	    }
	
	public void searchDrupal(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;

		} 
		
		waitForElementPresent(this.uiConstants.SEARCH, methodName);
		getXpathWebElement(this.uiConstants.SEARCH);
		sendKeys(this.phpConstants.SEARCH);
		waitForElementPresent(this.uiConstants.SUBMIT, methodName);
		getXpathWebElement(this.uiConstants.SUBMIT);
		click();
		Thread.sleep(3000);
		
		
	}
	

	public void myAccount(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;

		} 
		
		waitForElementPresent(this.uiConstants.MYACC, methodName);
		getXpathWebElement(this.uiConstants.MYACC);
		click();
		Thread.sleep(3000);
		
		
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

	

	public void isElementPresent(String element) throws Exception {

		WebElement testElement = getXpathWebElement(element);
		if (testElement.isDisplayed() && testElement.isEnabled()) {

			log.info("---Element found---");
		} else {
			throw new RuntimeException("--Element not found---");
			// Assert.fail("--Element is not present--"+testElement);

		}

	}
	/**
	 *  
	 * @param text
	 */
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
	
	


}

