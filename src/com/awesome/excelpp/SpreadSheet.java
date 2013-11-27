package com.awesome.excelpp;

import com.awesome.excelpp.gui.GUI;

public class SpreadSheet {
	public static void main(String[] args) {
<<<<<<< HEAD
		new SpreadSheetViewer();
		
		System.out.println("Bestand pad:");
		sc = new Scanner(System.in);
		path = sc.next();
		
		long startXML, endXML;
		startXML = System.currentTimeMillis();
		loadXML(path);
		endXML = System.currentTimeMillis();
		
		/*long startSAX, endSAX;
		startSAX = System.currentTimeMillis();
		loadXMLSAX(path);
		endSAX = System.currentTimeMillis();*/
		
		System.out.println("XML execution time: " + (endXML - startXML) + " ms. File has 10073 lines.");
		//System.out.println("XMLSAX execution time: " + (endSAX - startSAX) + " ms. File has 10082 lines.");
	}
	
	public static void loadXML(String path) {
		File file = new File(path);
		try {
			Document doc = XML.parse(file);
			XML.print(doc);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nTry again, the dom parser can't read it");
		}
	}
	
	public static void loadXMLSAX(String path) {
		File file = new File(path);
		try {
			XMLSAX xmlSAX = new XMLSAX(file);
			xmlSAX.print();
		} catch (Exception e) {
			System.out.println("\nTry again, the sax parser can't read it");
			e.printStackTrace();
		}
=======
		new GUI();
>>>>>>> 246ab21f440525bf1c6e080fcc20b68b7384c8fd
	}
}
