
package com.photon.phresco.Screens;

import java.io.IOException;

public class PhotonAbstractScreen extends BaseScreen {

	protected PhotonAbstractScreen(String selectedBrowser, String selectedPlatform,String url, 
				String contextName) throws IOException,
			Exception {
		super(selectedBrowser,selectedPlatform,url,contextName);
	}

}
