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
import org.junit.Assert;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WordPress;

public class BaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private  Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;
	private WordPress wordConstants;
	private UIConstants uiConstants;
	private UserInfoConstants userInfo;
	private  PhrescoUiConstants phrsc;
	DesiredCapabilities capabilities;
	
	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser,String selectedPlatform, String applicationURL,
			String applicationContext, WordPress wordConstants,
			UIConstants uiConstants,UserInfoConstants userInfo) throws AWTException, IOException, ScreenActionFailedException {

		this.wordConstants = wordConstants;
		this.uiConstants = uiConstants;
		this.userInfo=userInfo;
		try {
			instantiateBrowser(selectedBrowser,selectedPlatform, applicationURL, applicationContext);
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
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	
	public  void windowResize()
	{
		phrsc = new PhrescoUiConstants();
		String resolution = phrsc.RESOLUTION;		
		if(resolution!=null)
		{
		String[] tokens = resolution.split("x");
		String resolutionX=tokens[0];
		String resolutionY=tokens[1];		
		int x= Integer.parseInt(resolutionX);
		int y= Integer.parseInt(resolutionY);
		Dimension screenResolution = new Dimension(x,y);
		driver.manage().window().setSize(screenResolution);
		}
		else{
			driver.manage().window().maximize();
		}
	}
	/*
	 * public static void windowMaximizeFirefox() {
	 * driver.manage().window().setPosition(new Point(0, 0)); java.awt.Dimension
	 * screenSize = java.awt.Toolkit.getDefaultToolkit() .getScreenSize();
	 * Dimension dim = new Dimension((int) screenSize.getWidth(), (int)
	 * screenSize.getHeight()); driver.manage().window().setSize(dim); }
	 */

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
			WebDriverWait wait = new WebDriverWait(driver, 5);
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

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("Entering:------presenceOfElementLocated()-----Start");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				log.info("Entering:*********presenceOfElementLocated()******End");
				return driver.findElement(locator);

			}

		};

	}
	
	/*public void Search(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
		}
		Thread.sleep(3000);
	    waitForElementPresent(uiConstants.SEARCHFIELD,methodName);
	    getXpathWebElement(uiConstants.SEARCHFIELD);
	    sendKeys(userInfo.SEARCH_VALUE);	
	    submit();
	    Thread.sleep(3000);
	  
	   
	   
	}*/
	public void Login(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
		}
	 waitForElementPresent(uiConstants.LOGINLINK,methodName);
	 getXpathWebElement(uiConstants.LOGINLINK);
	 click();
	 Thread.sleep(3000);
	 waitForElementPresent(uiConstants.USERNAMEF,methodName);
	 getXpathWebElement(uiConstants.USERNAMEF);
	 sendKeys(userInfo.USERNAME_VALUE);
	 waitForElementPresent(uiConstants.PASSWORDF,methodName);
     getXpathWebElement(uiConstants.PASSWORDF);
     sendKeys(userInfo.PASSWORD_VALUE);
     Thread.sleep(3000);
     getXpathWebElement(uiConstants.BUTTONCLIK);
     click();
     Thread.sleep(5000);
     isTextPresent(wordConstants.TEXT);
     getXpathWebElement(uiConstants.LOGOUTWAIT);
     Thread.sleep(3000);
     click();
     Actions action = new Actions(driver);
     action.moveToElement(driver.findElement(By.xpath("//img")));
     action.perform();
     action.click(driver.findElement(By.xpath("//ul[2]/li/div/ul/li[3]/a"))); 
     action.perform();
     Thread.sleep(2000);
     waitForElementPresent(uiConstants.BACK,methodName);
	 getXpathWebElement(uiConstants.BACK);
	 click();
    
}
	public void PostComent(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
		}
		Thread.sleep(3000);
	    waitForElementPresent(uiConstants.HELLO_WORLD_LINK,methodName);
	    getXpathWebElement(uiConstants.HELLO_WORLD_LINK);
	    click();
	    	
	    waitForElementPresent(uiConstants.AUTHOR_NAME,methodName);
	    getXpathWebElement(uiConstants.AUTHOR_NAME);
	    sendKeys(userInfo.AUTHOR_NAME);
	    
	    waitForElementPresent(uiConstants.AUTHOR_EMAIL,methodName);
	    getXpathWebElement(uiConstants.AUTHOR_EMAIL);
	    sendKeys(userInfo.AUTHOR_EMAIL);
	    
	    waitForElementPresent(uiConstants.COMMENT_FIELD,methodName);
	    getXpathWebElement(uiConstants.COMMENT_FIELD);
	    sendKeys(userInfo.AUTHOR_COMMENT);
	    
	    waitForElementPresent(uiConstants.POST_LINK,methodName);
	    getXpathWebElement(uiConstants.POST_LINK);
	    click();
	    waitForElementPresent(userInfo.AUTHOR_COMMENT,methodName);
	    isTextPresent(userInfo.AUTHOR_COMMENT);
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

	/*public void isTextPresent(String textValue) {
		if (textValue != null) {
			Boolean textCheck = driver.getPageSource().contains(textValue);
			Assert.assertTrue("Text present", textCheck);
		} else {
			System.out.println("Text not present");
			throw new RuntimeException("----HelloWorld Text is not existed----");

		}
	}*/

	public void isElementPresent(String element) throws Exception {

		WebElement testElement = getXpathWebElement(element);
		if (testElement.isDisplayed() && testElement.isEnabled()) {

			log.info("---Element found---");
		} else {
			throw new RuntimeException("--Element not found---");
			// Assert.fail("--Element is not present--"+testElement);

		}

	}
	
	
	public boolean isTextPresent(String text) {
        boolean value=driver.findElement(By.tagName("body")).getText().contains(text);            
        System.out.println("-----TextCheck value-->"+value);
        Assert.assertTrue(value==true);
        
        return value;
        
        
        
    }   

}
