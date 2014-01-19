package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Rounds a number up, away from 0 (zero).
 * @author Team Awesome
 */
public class Roundup extends Formula {
	@Override
	public Double getValue(Object...args) throws MathException {
		if (args[0] instanceof Integer)
			args[0] = new Double((Integer)args[0]);
		else if (!(args[0] instanceof Double))
			throw new MathException();

		return Math.ceil((Double)args[0]);
	}
}
