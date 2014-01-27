package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * Returns a positive square root.
 * <p>Syntax: =Sqrt(number)</p>
 * @author Team Awesome
 */
public class Sqrt extends Formula {
	@Override
	public Double getValue(Object...args) throws MathException {
		if (args.length != 1)
			throw new MathException();

		return Math.sqrt(getDouble(args[0]));
	}
}	
