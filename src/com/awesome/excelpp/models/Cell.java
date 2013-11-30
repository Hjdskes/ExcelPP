package com.awesome.excelpp.models;

import java.lang.reflect.Constructor;
import java.util.Observable;

import com.awesome.excelpp.math.Formula;
import com.awesome.excelpp.scanner.CellScanner;

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
			return parseFormula(content.substring(1));
		
		return content;
	}
	
	/**
	 * Parses the operation stored in this Cells content
	 * @param operation		String representing an operation, for example ADD(1,2)
	 * @return				the result of the stored operation, as a String
	 */
	private String parseFormula(String formula) {
		CellScanner scanner = new CellScanner(formula);
		
		String value = "";
		if (scanner.hasNextWord()) {
			String formName = "com.awesome.excelpp.math." + scanner.next();
			String formLBracket = scanner.next();
			String arg1 = scanner.next();
			String arg2 = scanner.next();
			String formRBracket = scanner.next();
			
			if (formLBracket != null && formLBracket.equals("(") &&
				formRBracket != null && formRBracket.equals(")")) {
				value = getFormulaValue(formName, arg1, arg2);
			} else {
				value = "#ARGINV";
			}
		}
		return value;
	}
	
	private String getFormulaValue(String formName, String arg1, String arg2) {
		String value = "";
		try {
			Class<?> formClass = Class.forName(formName); 
			Constructor<?> formConstructor = formClass.getConstructor();
			Formula form = (Formula)formConstructor.newInstance();
			
			value = "" + form.getValue(arg1, arg2);
		} catch (NumberFormatException e) {
			value = "#NUMINV";
		} catch (Exception e) {
			value = "#OPINV";
		}
		return value;
	}
}
