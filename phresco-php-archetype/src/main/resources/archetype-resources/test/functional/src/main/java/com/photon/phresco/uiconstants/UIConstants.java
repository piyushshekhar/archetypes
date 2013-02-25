package com.photon.phresco.uiconstants;


import java.io.IOException;
import java.lang.reflect.Field;

public class UIConstants {
	

	
    private ReadXMLFile readXml;
    public String CREATENEWACC="createnewacc";
    public String NAME="name";
    public String EMAIL="email";
    public String SUBMIT="submit";
    public String LOGIN="login";
    public String LOGINPASSWORD="loginpassword";
    public String SEARCH="search";
    public String MYACC="myacc";
    public String LOGOUT="logout";
    

    
    /**
	 * Reading the UIConstants xml files through UIConstants() Constructor 
	 */
	
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
					localField
							.set(this, readXml.getValue((String) localObject));

			}
		} catch (Exception localException) {
			throw new RuntimeException("Loading "
					+ super.getClass().getSimpleName() + " failed",
					localException);
		}
	}
}
