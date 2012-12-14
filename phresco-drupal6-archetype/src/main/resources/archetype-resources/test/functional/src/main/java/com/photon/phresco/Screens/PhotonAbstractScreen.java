package com.photon.phresco.Screens;

import java.io.IOException;

import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.DrupalData;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class PhotonAbstractScreen extends BaseScreen {


	public PhotonAbstractScreen(){
	
	}
	


	protected PhotonAbstractScreen(String selectedBrowser,String selectedPlatform,String applicationURL,String applicationContext,DrupalData drupalConstants,UIConstants uiConstants,UserInfoConstants userInfo) throws IOException,
			Exception {
		super(selectedBrowser,selectedPlatform, applicationURL,applicationContext,drupalConstants,uiConstants, userInfo);

	

}
}