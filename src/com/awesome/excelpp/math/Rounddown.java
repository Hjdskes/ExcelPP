package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Rounds a number down, toward zero.
 * <p>Syntax: =Rounddown(number, num_digits)</p>
 * @author Team Awesome
 */
public class Rounddown extends Formula {
	@Override
	public Double getValue(Object...args) throws MathException {
		if (args.length != 1)
			throw new MathException();

		return Math.floor(getDouble(args[0]));
	}
}
