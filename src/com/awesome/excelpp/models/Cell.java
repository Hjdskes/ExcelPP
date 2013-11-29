package com.awesome.excelpp.models;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Scanner;

import com.awesome.excelpp.math.*;

public class Cell extends Observable {
	private String content; // =2+2
	
	/**
	 * Constructs a new Cell
	 * @param content	String with an unevaluated expression
	 */
	public Cell(String content) {
		this.content = content;
	}
	
	/**
	 * Gets the unevaluated content of this Cell
	 * 	Suppose the content of this Cell is "=4+4"
	 * 	This function will then return "=4+4"
	 * @return			String with an unevaluated expression
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Sets the unevaluated content of this Cell
	 * @param content	String with an unevaluated expression
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Gets the evaluated content of this Cell
	 * 	Suppose the content of this Cell is "=4+4"
	 * 	This function will then return "8"
	 * @return			String with an evaluated expression
	 */
	public String getValue() {
		if (content != null && content.charAt(0) == '=')
			return parseOperation(content.substring(1));
		
		return content;
	}
	
	private String parseOperation(String operation) {
		if (content.indexOf('(') == -1)
			return "#ERROR";
		
		String value;
		
		Scanner cellParser = new Scanner(operation);
		cellParser.useDelimiter("[\\(\\)]");
		
		String packageName = "com.awesome.excelpp.math";
		String opName = packageName + '.' + cellParser.next();
		
		try {
			Class<?> opClass = Class.forName(opName);
			Constructor<?> opConstructor = opClass.getConstructor();
			Formula op = (Formula)opConstructor.newInstance();
			
			Scanner opArgs = new Scanner(cellParser.next());
			opArgs.useDelimiter(",");
			value = Integer.toString(op.getValue(opArgs.nextInt(), opArgs.nextInt()));
			opArgs.close();
		} catch (Exception e) {
			value = "#OPINV";
		}
		
		cellParser.close();
		return value;
	}
}
