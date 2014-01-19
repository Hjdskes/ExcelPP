package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Converts all uppercase letters in a text string to lowercase.
 * @author Team Awesome
 */
public class Lower extends Formula {
	@Override
	public String getValue(Object... args) throws MathException {
		if(args[0] instanceof String) {
			String string = (String) args[0];
			return string.toLowerCase();
		} else {
			throw new MathException();
		}
	}
}