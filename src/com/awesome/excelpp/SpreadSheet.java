package com.awesome.excelpp;

import java.io.File;

import org.w3c.dom.Document;

import com.awesome.excelpp.gui.SpreadSheetViewer;
import com.awesome.excelpp.xml.XML;
import com.awesome.excelpp.xml.XMLSAX;


public class SpreadSheet {
	public static void main(String[] args) {
		new SpreadSheetViewer();
		
		long startXML, endXML;
		startXML = System.currentTimeMillis();
		loadXML();
		endXML = System.currentTimeMillis();
		
		long startSAX, endSAX;
		startSAX = System.currentTimeMillis();
		loadXMLSAX();
		endSAX = System.currentTimeMillis();
		
		System.out.println("XML execution time: " + (endXML - startXML) + " ms. File has 10073 lines.");
		System.out.println("XMLSAX execution time: " + (endSAX - startSAX) + " ms. File has 10082 lines.");
	}
	
	public static void loadXML() {
		Document doc = XML.parse(new File("data/test.xml"));
		XML.print(doc);
	}
	
	public static void loadXMLSAX() {
		try {
			XMLSAX xmlSAX = new XMLSAX(new File("data/testSAX.xml"));
			xmlSAX.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
