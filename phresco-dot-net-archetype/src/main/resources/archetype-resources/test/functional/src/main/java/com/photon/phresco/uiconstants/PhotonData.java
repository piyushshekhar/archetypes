package com.photon.phresco.uiconstants;

import java.io.IOException;
import java.lang.reflect.Field;

public class PhotonData {

	private ReadXMLFile readXml;
	public String USERNAME="username";
	public String CLIENTREFVALUE="clientrefvalue";
	public String CLIENTREFVALUE1="clientrefvalue1";
	public String SECIDVALUE="secidvalue";

	
	public PhotonData() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadphpData();
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
