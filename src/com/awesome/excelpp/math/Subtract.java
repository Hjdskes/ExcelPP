package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Class for the substract formula
 *
 */
public class Subtract extends Formula {
	public double getValue(Object ... args) throws MathException {
		double res;
		if (args[0] instanceof Integer) {
			res = new Double((Integer)args[0]);
		} else if (args[0] instanceof Double) {
			res = (Double)args[0];
		} else {
			throw new MathException();
		}
		
		for (int i = 1; i < args.length; i++) {
			if (args[i] instanceof Integer)
				args[i] = new Double((Integer)args[i]);
			
			if (!(args[i] instanceof Double))
				throw new MathException();
			
			res -= (Double)args[i];
		}
	        
		return res;
	}
}
