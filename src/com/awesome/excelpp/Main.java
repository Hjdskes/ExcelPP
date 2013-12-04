package com.awesome.excelpp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.awesome.excelpp.gui.GUI;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.xml.XML;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, TransformerException {
		new GUI();
		Document doc = XML.parse(new File("data/demo.xml"));
		XML.print(doc);
		
		/* test for the write*/
		//XML.write(doc, "data/write.xml");
		
		SpreadSheet test = XML.print(doc);
		test.toXML("data/output.xml");
	}
}
