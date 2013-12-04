package com.awesome.excelpp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.awesome.excelpp.gui.GUI;
import com.awesome.excelpp.xml.XML;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {
		new GUI();
		Document doc = XML.parse(new File("data/demo.xml"));
		XML.print(doc);
	}


/** VAN AF BLIJVEN!! OVERRULE VAN DE GUI OM TE TESTEN!! */
/*Je kan dit zelf afsterren bij jullie */
/*	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {
		Document doc = XML.parse(new File("data/demo.xml"));
		XML.print(doc);
	}*/
}
