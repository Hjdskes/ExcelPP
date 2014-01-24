package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Converts text to uppercase.
 * <p>Syntax: =Upper(text)</p>
 * @author Team Awesome
 */
public class Upper extends Formula {
	@Override
	public String getValue(Object...args) throws MathException {
		if (args.length != 1 || !(args[0] instanceof String))
			throw new MathException();
		return getString(args[0]).toUpperCase();
	}
}
