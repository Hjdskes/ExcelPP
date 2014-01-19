package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns a positive square root.
 * @author Team Awesome
 */
public class Sqrt {
	public Double getValue(Object...args) throws MathException {
		if (args[0] instanceof Integer)
			args[0] = new Double((Integer)args[0]);
		else if (!(args[0] instanceof Double))
			throw new MathException();

		return Math.sqrt((Double)args[0]);
	}
}	
