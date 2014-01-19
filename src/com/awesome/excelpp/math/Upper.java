package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Converts text to uppercase.
 * @author Team Awesome
 */
public class Upper extends Formula {
	public String getValue(Object...args) throws MathException {
		if (args.length != 1)
			throw new MathException();

		return getString(args[0]).toUpperCase();
	}
}
