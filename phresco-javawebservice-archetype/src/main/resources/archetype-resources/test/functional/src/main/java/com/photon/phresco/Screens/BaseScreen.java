package com.photon.phresco.Screens;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriverService;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoJavaWebserviceUiConstants;


public class BaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	DesiredCapabilities capabilities;
	private Log log = LogFactory.getLog("BaseScreen");

	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String selectedPlatform,String applicationURL,String applicatinContext)
	throws AWTException, IOException, ScreenActionFailedException {

		try {			
			instantiateBrowser(selectedBrowser,selectedPlatform,applicationURL, applicatinContext);
		} 
		catch (ScreenException e) {
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
			/*	capabilities.setCapability("safari.autostart ", true);*/
				System.out.println("-----------checking the SAFARI-------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("-------------***LAUNCHING FIREFOX***--------------");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
		}
		else if (selectedBrowser.equalsIgnoreCase(Constants.HTML_UNIT_DRIVER)) {
			log.info("-------------***HTML_UNIT_DRIVER***--------------");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("htmlunit"); 
			/*URL server = new URL("http://testVM:4444/wd/hub");
			new RemoteWebDriver(new Url("http://testVM:4444/wd/hub");*/

			System.out.println("-----------checking the HTML_UNIT_DRIVER-------");
			// break;
			// driver = new RemoteWebDriver(server, capabilities);

		}
		else {
			throw new ScreenException(
			"------Only FireFox,InternetExplore and Chrome works-----------");
		}
		if (selectedPlatform.equalsIgnoreCase("WINDOWS")) {
			capabilities.setCapability(CapabilityType.PLATFORM,Platform.WINDOWS);

		} else if (selectedPlatform.equalsIgnoreCase("LINUX")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);

		} else if (selectedPlatform.equalsIgnoreCase("MAC")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.MAC);

		}
		driver = new RemoteWebDriver(server, capabilities);
		driver.get(applicationURL + applicationContext);


	}



	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");		
		if (driver != null) {
			driver.quit();		
			if(chromeService!=null){
				chromeService.stop();
			}
		} else {
			throw new NullPointerException();
		}

	}

	public  String  getChromeLocation(){	
		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();		
		String location = directory + targetDirectory;	
		return location;
	}


	public  String getChromeFile(){
		if(System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)){
			log.info("*******WINDOWS MACHINE FOUND*************");
			return Constants.WINDOWS_DIRECTORY + "/chromedriver.exe" ;			
		}else if(System.getProperty("os.name").startsWith(Constants.LINUX_OS)){
			log.info("*******LINUX MACHINE FOUND*************");
			return Constants.LINUX_DIRECTORY_64+"/chromedriver";
		}else if(System.getProperty("os.name").startsWith(Constants.MAC_OS)){
			log.info("*******MAC MACHINE FOUND*************");
			return Constants.MAC_DIRECTORY+"/chromedriver";
		}else{
			throw new NullPointerException("******PLATFORM NOT FOUND********");
		}

	}

	public boolean isTextPresent(String text)
	{
		if(text!=null)
		{
			boolean value=driver.findElement(By.tagName("body")).getText().contains(text);
			//System.out.println("--------TextCheck value---->"+text+"------------Result is-------------"+value); 
			AssertJUnit.assertTrue(value);
			return value;
		}
		else
		{
			throw new RuntimeException("---- Text not present----");
		}

	}




	public  void javaWebservcieHelloWorld(String methodName,PhrescoJavaWebserviceUiConstants javaWservice) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebservcieHelloWorld::******executing javaWebservcieHelloWorld scenario****");
		try {
			Thread.sleep(5000);	
			isTextPresent(javaWservice.ELEMENT);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
}


