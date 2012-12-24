package com.photon.phresco.uiconstants;


import java.lang.reflect.Field;

public class UIConstants {
	

	
	private ReadXMLFile readXml;

	
    public String USERNAMEF = "username";
    public String PASSWORDF = "password";
    public String BUTTONCLIK="buttonclik";
    public String REGISTERLINK = "registerlink";
    public String NEWUSERNAME = "newuser";
    public String NEWEMAIL= "newemail";
    public String LOGOUT="logout";
    public String REQUEST_LINK="requestlink";
    public String RMAIL="rmail";
    
	public UIConstants() {
		try {
			readXml = new ReadXMLFile();		
			readXml.loadUIConstants();
			Field[] arrayOfField1 = super.getClass().getFields();
			Field[] arrayOfField2 = arrayOfField1;
			int i = arrayOfField2.length;
			for (int j = 0; j < i; ++j) {
				Field localField = arrayOfField2[j];
				Object localObject = localField.get(this);
				if (localObject instanceof String)
					localField.set(this, readXml.getValue((String) localObject));

			}
		} catch (Exception localException) {
			throw new RuntimeException("Loading "
					+ super.getClass().getSimpleName() + " failed",
					localException);
		}
	}
}
