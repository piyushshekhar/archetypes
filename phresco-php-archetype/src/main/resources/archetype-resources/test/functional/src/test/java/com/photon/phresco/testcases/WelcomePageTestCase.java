package com.photon.phresco.testcases;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhpData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class WelcomePageTestCase {

	private  UIConstants uiConstants;
	private  PhrescoUiConstants phrescoUIConstants;
	private  WelcomeScreen welcomeScreen;
	private  String methodName;
	private  String selectedBrowser;
	private  PhpData PhpConstants;
	private  UserInfoConstants userInfo;

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			PhpConstants = new PhpData();
			userInfo= new UserInfoConstants();
			String selectedBrowser = browser;
			String selectedPlatform = platform;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
	
			/*Reporter.log("Selected Browser to execute testcases--->>"
					+ selectedBrowser);*/
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser, selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, PhpConstants, uiConstants, userInfo);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

/*	public  void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser, selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, PhpConstants, uiConstants, userInfo);
		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}*/
	
@Test
	public void testToVerifyTextPresent()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToCreateAccount()-------------");
		
		     welcomeScreen.VerifyTextPresent(methodName);
			
			
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
@Test
public void testToVerifyTextNotPresent()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testToCreateAccount()-------------");
	
	     welcomeScreen.VerifyTextNotPresent(methodName);
		
		
	
	} catch (Exception t) {
		t.printStackTrace();

	}
}
	
	@AfterTest
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}