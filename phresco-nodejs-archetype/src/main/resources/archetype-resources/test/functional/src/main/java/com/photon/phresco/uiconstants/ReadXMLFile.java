
package com.photon.phresco.uiconstants;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.photon.phresco.selenium.util.ScreenException;


public class ReadXMLFile {

	private  Element eElement;
	private Log log = LogFactory.getLog(getClass());
	private final String phrsc = "./src/main/resources/phresco-env-config.Xml";
	private final String nodejs = "./src/main/resources/NodejsData.xml";
	private final String nodeui = "./src/main/resources/UIConstants.xml";
	private final String nodeinfo = "./src/main/resources/UserInfo.xml";

	public ReadXMLFile() throws ScreenException {
		log.info("@ReadXMLFile Constructor::loading *****PhrescoUIConstants******");
		loadPhrescoConstansts(phrsc);
	}

	public void loadPhrescoConstansts(String properties) throws ScreenException {

		try {
			File fXmlFile = new File(properties);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
			.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName("environment");
			System.out.println("-----------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void NodejsData() throws ScreenException {
		loadPhrescoConstansts(nodejs);
	}
	public void loadUIConstants() throws ScreenException {
		loadPhrescoConstansts(nodeui);
	}

	public void loadUserInfoConstants() throws ScreenException {
		loadPhrescoConstansts(nodeinfo);

	}


	public String getValue(String elementName) throws Exception {

		NodeList nlList = eElement.getElementsByTagName(elementName).item(0)
		.getChildNodes();
		Node nValue = (Node) nlList.item(0);		
		if (nValue.getNodeValue() == null) {
			throw new Exception("***The element value is zero***");
		} 

		return nValue.getNodeValue();
	}






}