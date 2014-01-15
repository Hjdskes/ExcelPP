package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Class for the substract formula
 *
 */
public class Subtract extends Formula {
	public double getValue(Object ... args) throws MathException {
		if (!(args[0] instanceof Double))
			throw new MathException();
		
		double res = (Double)args[0];
		
		for(int i = 1; i < args.length; i++) {
			if (!(args[i] instanceof Double))
				throw new MathException();
			
			res -= (Double)args[i];
		}
		return res;
	}
}
