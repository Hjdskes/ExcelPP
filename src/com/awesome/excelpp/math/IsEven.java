package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * Returns true if number is even, or false if number is odd.
 * <p>Syntax: =IsEven(value)</p> 
 * @author Team Awesome
 */
public class IsEven extends Formula {
	@Override
	public Boolean getValue(Object ... args) throws MathException {
		if (args.length != 1)
			throw new MathException();

		return getDouble(args[0]) % 2.0 == 0.0 ? true : false;
	}
}
