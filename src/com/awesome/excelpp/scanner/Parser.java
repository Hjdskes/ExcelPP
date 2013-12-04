package com.awesome.excelpp.scanner;

import java.lang.reflect.Constructor;

import com.awesome.excelpp.math.Formula;

public class Parser {
	/**
	 * Parses the operation stored in this Cells content
	 * @param operation		String representing an operation, for example ADD(1,2)
	 * @return				the result of the stored operation, as a String
	 */
	public String parseFormula(String formula) {
		Lexer scanner = new Lexer(formula);
		
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
		} else {
			value = "#OPINV";
		}
		return value;
	}
	
	private String getFormulaValue(String formName, String arg1, String arg2) {
		String value = "";
		try {
			Class<?> formClass = Class.forName(formName); 
			Constructor<?> formConstructor = formClass.getConstructor();
			Formula form = (Formula)formConstructor.newInstance();
			
			value = "" + form.getValue(Integer.parseInt(arg1), Integer.parseInt(arg2));
		} catch (NumberFormatException e) {
			value = "#NUMINV";
		} catch (Exception e) {
			value = "#OPINV";
		}
		return value;
	}
}
