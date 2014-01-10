package com.awesome.excelpp.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.awesome.excelpp.models.SpreadSheet;

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
	public static SpreadSheet print(Document doc){
		SpreadSheet sheet = new SpreadSheet();
		doc.getDocumentElement().normalize();
		
		/* The master tag to identify the file */
		//System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		NodeList list = doc.getElementsByTagName("CELL");
		//System.out.println("----------------------------");
		
			String row = "";
		    String column = "";
		    String data = "";

		    for (int i = 0; i < list.getLength(); i++) {

		      Node node = list.item(i);
		      
		      data = node.getFirstChild().getNodeValue();
		      NamedNodeMap attributes = node.getAttributes();

		      for (int a = 0; a < attributes.getLength(); a++) {
				
		        Node theAttribute = attributes.item(a);
		        
		        String temp = theAttribute.getNodeName();
		        
		        String data_temp = theAttribute.getNodeValue();
		      
		        if(temp.equals("row")) {
		        	row = data_temp;
		        }
		        else if(temp.equals("column")) {
		        	column = data_temp;
		        }
		        else {
		        	System.out.println("Row not found");
		        }
		        theAttribute.getNodeValue();
		      }
		      
		      int row_out = Integer.parseInt(row);
		      int col_out = Integer.parseInt(column);
		      
		      sheet.setValueAt(data, row_out - 1, col_out - 1);
		    }
		    return sheet;
		  }
}
