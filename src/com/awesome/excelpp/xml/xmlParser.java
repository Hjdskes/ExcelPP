package com.awesome.excelpp.xml;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Joris Lamers
 *
 */
public class xmlParser {	
	/**
	 * 
	 */
	public static void parse() {
		try {
			/* the xml file to read */
			File xmlFile = new File("XML/test.xml");
			
			/* The document builder from the xml */
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			/* The master tag to identify the file */
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			
			NodeList list = doc.getElementsByTagName("row");
			System.out.println("----------------------------");
			
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				String col = "|";
				NodeList columnList = el.getElementsByTagName("column");
				
				for(int j = 0; j < columnList.getLength(); j++) {
					col = col + el.getElementsByTagName("column").item(j).getTextContent() + "|";
				}
				System.out.println(col);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}
}
