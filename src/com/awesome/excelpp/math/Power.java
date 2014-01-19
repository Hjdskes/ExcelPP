package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Power extends Formula {
	public Double getValue(Object ... args) throws MathException {
		if((args[0] instanceof Double || args[0] instanceof Integer) && (args[1] instanceof Double || args[1] instanceof Integer)) {
					return (Math.pow((double)args[0], (double)args[1]));
		}
		else {
			throw new MathException();
		}
	}
}
