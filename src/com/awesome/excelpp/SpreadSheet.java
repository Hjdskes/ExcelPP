package com.awesome.excelpp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Document;

import com.awesome.excelpp.gui.SpreadSheetViewer;
import com.awesome.excelpp.xml.XML;
import com.awesome.excelpp.xml.XMLSAX;


public class SpreadSheet {
	static Scanner sc;
	private static String path;
	
	public static void main(String[] args) {
		new SpreadSheetViewer();
		
		System.out.println("Bestand pad:");
		sc = new Scanner(System.in);
		path = sc.next();
		
		long startXML, endXML;
		startXML = System.currentTimeMillis();
		loadXML(path);
		endXML = System.currentTimeMillis();
		
		long startSAX, endSAX;
		startSAX = System.currentTimeMillis();
		loadXMLSAX(path);
		endSAX = System.currentTimeMillis();
		
		System.out.println("XML execution time: " + (endXML - startXML) + " ms. File has 10073 lines.");
		System.out.println("XMLSAX execution time: " + (endSAX - startSAX) + " ms. File has 10082 lines.");
	}
	
	public static void loadXML(String path) {
		File file = new File(path);
		try {
			Document doc = XML.parse(file);
			XML.print(doc);
		} catch (Exception e) {
			System.out.println("Try again, the dom parser can't read it");
			main(null);
		}
	}
	
	public static void loadXMLSAX(String path) {
		File file = new File(path);
		try {
			XMLSAX xmlSAX = new XMLSAX(file);
			xmlSAX.print();
		} catch (Exception e) {
			System.out.println("Try again, the sax parser can't read it");
			main(null);
		}
	}
}
