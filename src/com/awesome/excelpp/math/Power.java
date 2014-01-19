package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the result of the first argument raised to the second argument.
 * @author Team Awesome
 */
public class Power extends Formula {
	public Double getValue(Object ... args) throws MathException {
		if (args.length != 2)
			throw new MathException();

		return (Math.pow(getDouble(args[0]), getDouble(args[1])));
	}
}
