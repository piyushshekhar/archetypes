package com.photon.phresco.Screens;

import java.io.IOException;

import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.DrupalData;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class WelcomeScreen extends PhotonAbstractScreen {
	public WelcomeScreen(String selectedBrowser,String selectedPlatform, String applicationURL,
			String applicationContext,DrupalData drupalConstants, UIConstants uiConstants,UserInfoConstants userInfo)
			throws InterruptedException, IOException, Exception {
		super(selectedBrowser,selectedPlatform, applicationURL, applicationContext,
				drupalConstants, uiConstants, userInfo);

	}

}	



/*public class WelcomeScreen extends PhotonAbstractScreen {

    public WelcomeScreen(String host, int port, String browser, String url, String speed,String context ) throws InterruptedException,IOException, Exception {
    	super(host, port, browser, url, speed, context);
    	
    	   
    }
 public MenuScreen menuScreen(UIConstants phrsc) throws Exception {
        
    	return new MenuScreen(phrsc);
    }
    

}*/
