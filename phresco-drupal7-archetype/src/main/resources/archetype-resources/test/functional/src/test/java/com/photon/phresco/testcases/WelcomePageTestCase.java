package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.DrupalData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class WelcomePageTestCase {

	private  UIConstants uiConstants;
	private  PhrescoUiConstants phrescoUIConstants;
	private  WelcomeScreen welcomeScreen;
	private  String methodName;
	private  String selectedBrowser;
	private  DrupalData drupalConstants;
	private  UserInfoConstants userInfo;

	/**
	 * Initializing the Object of a class PhrescoUiConstants, UIConstants, PhpData, UserInfoConstants
	 * @throws Exception
	 */
	@Parameters(value ={ "browser", "platform"})
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception {
		try {
			
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			drupalConstants = new DrupalData();
			userInfo = new UserInfoConstants();
			String selectedBrowser = browser;
			String selectedPlatform = platform;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, drupalConstants, uiConstants, userInfo);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Capturing the URL values through String & passing those values into WelcomeScreen
	 * @throws Exception
	 */
	
	/*public  void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, drupalConstants, uiConstants, userInfo);
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}*/

	/**
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws Exception
	 * In this Method just triggering test case against loginPhp,Create, Update, Delete Category BaseScreeen
	 */
	
	@Test
	public void testToCreateAccount()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToCreateAccount()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			welcomeScreen.createAccount(methodName);
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testToLogin()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToLogin()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			welcomeScreen.loginDrupal(methodName);
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	/**
	 * 	Triggering close method in BaseScreen 
	 */
	
	@AfterTest
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}