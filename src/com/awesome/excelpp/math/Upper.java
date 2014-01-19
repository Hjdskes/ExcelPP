package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;


public class Upper extends Formula {
	public String getValue(Object... args) throws MathException {
		if(args[0] instanceof String) {
		String string = (String) args[0];
		string = string.toUpperCase();
		return string;
		}
		else {
			throw new MathException();
		}
	}
}
