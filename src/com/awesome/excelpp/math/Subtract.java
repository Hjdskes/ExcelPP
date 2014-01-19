package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The subtract function returns the value of the first argument, minus all the other arguments.
 * @author Team Awesome
 */
public class Subtract extends Formula {
	public Object getValue(Object ... args) throws MathException {
		if (args.length == 1)
			throw new MathException();
		
		double res = getDouble(args[0]);
		for (int i = 1; i < args.length; i++) {
			res -= getDouble(args[i]);
		}
		return res;
	}
}
