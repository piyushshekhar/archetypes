package com.photon.phresco.testcases;

import java.io.IOException;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WordPress;

public class WelcomePageTestCase {

	private  UIConstants uiConstants;
	private  PhrescoUiConstants phrescoUIConstants;
	private  WelcomeScreen welcomeScreen;
	private  String methodName;
	private  String selectedBrowser;
	private  WordPress wordConstants;
	private  UserInfoConstants userInfo;

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			wordConstants = new WordPress();
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
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, wordConstants, uiConstants, userInfo);
			
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
			welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, wordConstants, uiConstants, userInfo);
		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}*/
	
	
@Test
	public void testToLogin()
			throws InterruptedException, IOException, Exception {
		try {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("---------testToLogin()-------------");
		
			welcomeScreen.Login(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
@Test
	public void testToPostComment()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("--------testToSearch()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		     welcomeScreen.PostComent(methodName);
			
			
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
@AfterTest
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}