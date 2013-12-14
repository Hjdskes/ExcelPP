package com.awesome.excelpp.models;

import java.util.Observable;

import com.awesome.excelpp.scanner.Parser;

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
		if (content != null && content.charAt(0) == '=') {
			Parser parse = new Parser(content.substring(1));
			return parse.getValue();
		}
		
		return content;
	}
}
