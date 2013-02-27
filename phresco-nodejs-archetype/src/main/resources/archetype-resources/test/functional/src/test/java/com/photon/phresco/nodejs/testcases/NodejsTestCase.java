
package com.photon.phresco.nodejs.testcases;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoNodejsUiConstants;
import com.photon.phresco.uiconstants.PhrescoUiConstants;



public class NodejsTestCase {


	private  PhrescoUiConstants phrscEnv;
	private  PhrescoNodejsUiConstants nodejs;
	private  WelcomeScreen welcomeScreen;
	private String methodName;

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public  void setUp(String browser,String platform) throws Exception
	{

		phrscEnv=new PhrescoUiConstants();
		nodejs=new PhrescoNodejsUiConstants();
		String selectedBrowser = browser;
		String selectedPlatform = platform;

		methodName = Thread.currentThread().getStackTrace()[1]
		                                                    .getMethodName();
		Reporter.log("Selected Browser to execute testcases--->>"
				+ selectedBrowser);
		String applicationURL = phrscEnv.PROTOCOL + "://"
		+ phrscEnv.HOST + ":" + phrscEnv.PORT
		+ "/";
		welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform,applicationURL,
				phrscEnv.CONTEXT);


	}


	@Test
	public void testHelloWorldPage()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testHelloWorldPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			welcomeScreen.nodejsHelloWorld(methodName,nodejs);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@AfterTest
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}







