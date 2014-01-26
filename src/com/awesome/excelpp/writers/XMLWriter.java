package com.awesome.excelpp.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import com.awesome.excelpp.models.Cell;

/**
 * The <code>XMLWriter</code> class is used by <code>SpreadSheet</code> to write XML files to disk.
 * @author Team Awesome
 */
public class XMLWriter implements Writer {
	PrintWriter pw;
	
	/**
	 * Creates an <code>XMLWriter</code> class which will write to the specified <code>File</code>.
	 * @param file The <code>File</code> to write
	 * @throws FileNotFoundException
	 */
	public XMLWriter(File file) throws FileNotFoundException {
		pw = new PrintWriter(file);
		pw.write("<?xml version=\"1.0\"?>\n");
		pw.write("<SPREADSHEET>\n");
	}
	
	@Override
	public void addCell(Cell cell, int row, int col) {
		String content = forXML(cell.getContent());
		String bold = "";
		String italic = "";
		String fontColor = "";
		String bgColor = "";
		if(cell.getBold() != 0) {
			bold = "bold:" + cell.getBold() + ";";
		}
		if(cell.getItalic() != 0) {
			italic = "italic:" + cell.getItalic() + ";";
		}
		if(!cell.getBackgroundColorHex().equals("#FFFFFF")) {
			fontColor = "fontColor:" + cell.getForegroundColorHex() + ";";
		}
		if(!cell.getForegroundColorHex().equals("#000000")) {
			bgColor = "bgColor:" + cell.getBackgroundColorHex();
		}
		
			pw.write("<CELL row=\"" + row + "\"" +
						" column=\"" + col + "\"" +
						" style=\"" +
							bold +
							italic +
							fontColor +
							bgColor +
						"\"" +
						">" + content + "</CELL>\n");
		}

	@Override
	public void close() {
		pw.write("</SPREADSHEET>");
		pw.flush();
		pw.close();
	}
	
	 public static String forXML(String Text){
		    final StringBuilder result = new StringBuilder();
		    final StringCharacterIterator iterator = new StringCharacterIterator(Text);
		    char character =  iterator.current();
		    while (character != CharacterIterator.DONE ){
		      if (character == '<') {
		        result.append("&lt;");
		      }
		      else if (character == '>') {
		        result.append("&gt;");
		      }
		      else if (character == '\"') {
		        result.append("&quot;");
		      }
		      else if (character == '\'') {
		        result.append("&#039;");
		      }
		      else if (character == '&') {
		         result.append("&amp;");
		      }
		      else {
		        //the char is not a special one
		        //add it to the result as is
		        result.append(character);
		      }
		      character = iterator.next();
		    }
		    return result.toString();
		  }
}
