package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the remainder after the first argument is divided by the second argument.
 * <p>The result has the same sign as divisor.</p>
 * @author Team Awesome
 */
public class Mod extends Formula {
	public Double getValue(Object...args) throws MathException {
		if (args.length != 2)
			throw new MathException();

		return getDouble(args[0]) % getDouble(args[1]);
	}
}
