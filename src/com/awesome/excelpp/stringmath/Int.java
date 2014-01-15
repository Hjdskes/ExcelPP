package com.awesome.excelpp.stringmath;

import com.awesome.excelpp.math.exception.MathException;

public class Int extends StringFormula {
	public String getValue(String...vars) throws MathException {
		
		try {
		double temp = Double.parseDouble(vars[0]);
		int integer = (int) temp;
		String res = String.valueOf(integer);
		return res;
		
		} catch(Exception e) {
			throw new MathException();
		}
	}
}
