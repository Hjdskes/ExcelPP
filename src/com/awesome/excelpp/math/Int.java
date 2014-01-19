package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Int extends Formula {
	public String getValue(Object...args) throws MathException {
		
		try {
		double temp = Double.parseDouble((String) args[0]);
		int integer = (int) temp;
		String res = String.valueOf(integer);
		return res;
		
		} catch(Exception e) {
			throw new MathException();
		}
	}
}
