package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * Determines the sign of a number.
 * <p>Returns 1 if the number is positive, zero (0) if the number is 0, and -1 if the number is negative.
 * </br>Syntax: =Sign(number)</p> 
 * @author Strilanc, first answer: http://stackoverflow.com/questions/3994531/how-to-determine-if-a-number-is-positive-or-negative-in-java
 */
public class Sign extends Formula {
	@Override
	public Integer getValue(Object... args) throws MathException {
		if(args.length != 1)
			throw new MathException();

		if(args[0] instanceof Integer) {
			Integer res = (Integer)args[0];
			if (res == 0)
				return 0;
			if (res >> 31 != 0)
				return -1;
			return 1;
		} else if(args[0] instanceof Double) {
			Double res = (Double)args[0];
			if (res == 0.0)
				return 0;
			res *= Double.POSITIVE_INFINITY;
			if (res == Double.POSITIVE_INFINITY)
				return 1;
			if (res == Double.NEGATIVE_INFINITY)
				return -1;
		} else
			throw new MathException();

		return 0; //never reached
	}
}
