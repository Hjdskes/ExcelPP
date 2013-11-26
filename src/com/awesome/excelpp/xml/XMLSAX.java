package com.awesome.excelpp.xml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class XMLSAX extends DefaultHandler
{
	private String path = "";
	private int row;
	private int col;
	private String value = "";
	
    public XMLSAX(File file) throws IOException, SAXException {
    	super();
    	
    	XMLReader xr = XMLReaderFactory.createXMLReader();
		xr.setContentHandler(this);
		xr.setErrorHandler(this);
		
	    FileReader r = new FileReader(file);
		xr.parse(new InputSource(r));
    }
    
    public void startElement (String uri, String name, String qName, Attributes atts) {
    	path = path + "/" + name;
    	
    	if (path.equals("/SPREADSHEET/CELL")) {
    		row = Integer.parseInt(atts.getValue(0));
    		col = Integer.parseInt(atts.getValue(1));
    	}
	}

	public void endElement (String uri, String name, String qName) {
		if (path.equals("/SPREADSHEET/CELL")) {
			System.out.println("row: " + row + " col: " + col + " value: " + value.trim());
		}
		
		path = path.substring(0, path.length() - name.length() - 1);
		value = "";
	}
	
	public void characters (char ch[], int start, int length) {
		if (path.equals("/SPREADSHEET/CELL")) {
			value += new String(ch, start, length);
		}
	 }
    
	public void read() {
		
	}

	public void write()	{
		
	}
}
