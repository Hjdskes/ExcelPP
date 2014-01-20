package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Rounds a number down to the nearest integer.
 * <p>Syntax: Int(number);</p>
 * @author Team Awesome
 */
public class Int extends Formula {
	@Override
	public Integer getValue(Object ... args) throws MathException {
		if (args.length != 1)
			throw new MathException();
		
		return getInteger(args[0]);
	}
}
