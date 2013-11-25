package com.awesome.excelpp;

import java.io.File;

import org.w3c.dom.Document;

import com.awesome.excelpp.gui.MainScreen;
import com.awesome.excelpp.xml.XML;
import com.awesome.excelpp.xml.XMLSAX;


public class SpreadSheet {
	public static void main(String[] args) {
		new MainScreen();
		Document doc = XML.parse(new File("data/test.xml"));
		XML.print(doc);
		
		try {
			new XMLSAX(new File("data/testSAX.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
