package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;

public class WelcomePageTestCase {

	private  PhrescoUiConstants phrescoUIConstants;
	private  WelcomeScreen welcomeScreen;
	private  String selectedBrowser;
	private  String methodName;

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			String selectedBrowser = browser;
			String selectedPlatform = platform;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
	
			/*Reporter.log("Selected Browser to execute testcases--->>"
					+ selectedBrowser);*/
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT,phrescoUIConstants);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/*public static void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT,phrescoUIConstants);
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}*/

	@Test
	public void testWelcomePageScreen() throws InterruptedException,
			IOException, Exception {
		try {
			
			welcomeScreen.testHellow_world_text(methodName);
			
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testZFailureScenario() throws InterruptedException,
			IOException, Exception {
		try {
		
			welcomeScreen.testFailureCase(methodName);
			
	
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@AfterTest
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}