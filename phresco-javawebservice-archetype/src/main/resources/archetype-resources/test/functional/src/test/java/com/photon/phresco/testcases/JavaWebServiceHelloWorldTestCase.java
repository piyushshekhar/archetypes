
package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoJavaWebserviceUiConstants;
import com.photon.phresco.uiconstants.PhrescoUiConstants;



public class JavaWebServiceHelloWorldTestCase {

	
	private  PhrescoUiConstants phrscEnv;
	private  PhrescoJavaWebserviceUiConstants javaWservice;
	private  WelcomeScreen welcomeScreen;
	private  String methodName;
	

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public void setUp(String browser,String platform) throws Exception
	{
		
		phrscEnv=new PhrescoUiConstants();
		javaWservice=new PhrescoJavaWebserviceUiConstants();
		
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		methodName = Thread.currentThread().getStackTrace()[1]
		                                					.getMethodName();
		Reporter.log("Selected Browser to execute testcases--->>"
		                                					+ selectedBrowser);
		String applicationURL = phrscEnv.PROTOCOL + "://"+ phrscEnv.HOST + ":" + phrscEnv.PORT+ "/";
	
		welcomeScreen = new WelcomeScreen(selectedBrowser,
				selectedPlatform, applicationURL,
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
			
			welcomeScreen.javaWebservcieHelloWorld(methodName, javaWservice);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@AfterTest
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}
	
	





