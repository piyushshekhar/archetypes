package com.photon.phresco.Screens;

import java.io.IOException;


import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.PhotonData;
import com.photon.phresco.uiconstants.UserInfoConstants;

/**
 * Invoking the super class method through passing the vale of Browser,URL, Context, then PhpData,UIConstants,UserInfoConstants Xml Values
 * @throws InterruptedException
 * @throws IOException
 * @throws Exception
 */

public class WelcomeScreen extends PhotonAbstractScreen {
	public WelcomeScreen(String selectedBrowser, String applicationURL,
			String applicationContext,PhotonData phpConstants, UIConstants uiConstants, UserInfoConstants userinfo)
			throws InterruptedException, IOException, Exception {
		super(selectedBrowser, applicationURL, applicationContext,
				phpConstants, uiConstants, userinfo);

	}

}	




