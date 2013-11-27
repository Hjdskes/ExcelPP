package com.awesome.excelpp.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**Deze klasse parset van en write naar XML files.
 * 
 */
public class XML {
	
	/**Parset een XML bestand naar een Document object.
	 * 
	 * @param file - Het XML bestand.
	 * @return doc - Document object met data van het XML bestand.
	 */
	public static Document parse(File file) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		return doc;
	}
	
	/**Print een Document in tabelformaat.
	 * 
	 * @param doc - Een Document, eventueel ingelezen uit een XML bestand.
	 */
	public static void print(Document doc){
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
				if(j != 0){					
					System.out.print("---+");
				}else{
					System.out.print("+---+");
				}
				col += el.getElementsByTagName("column").item(j).getTextContent() + "|";
			}
		
			System.out.println();
			System.out.println(col);
		}
	}
	
	/**Schrijft een Document object weg naar een File.
	 * 
	 * @param doc - Document object met data voor het XML bestand.
	 * @param file - Het XML bestand.
	 * @throws TransformerException
	 */
	public static void write(Document doc, File file) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		//StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
	}

}
