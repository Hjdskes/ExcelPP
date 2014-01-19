package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Converts text to uppercase.
 * @author Team Awesome
 */
public class Upper extends Formula {
	public String getValue(Object... args) throws MathException {
		if(args[0] instanceof String) {
			String string = (String) args[0];
			return string.toUpperCase();
		} else {
			throw new MathException();
		}
	}
}
