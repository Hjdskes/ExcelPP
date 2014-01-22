package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Rounds a number up, away from 0 (zero).
 * <p>Syntax: =Roundup(number, num_digits);</p>
 * @author Team Awesome
 */
public class Roundup extends Formula {
	@Override
	public Double getValue(Object...args) throws MathException {
		if (args.length != 1)
			throw new MathException();
		return Math.ceil(getDouble(args[0]));
	}
}
