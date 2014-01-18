package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class IsEven extends Formula {
	@Override
	public double getValue(Object ... args) throws MathException {
		if (!(args.length == 1 && args[0] instanceof Double))
			throw new MathException();
		
		double res = 0.0;
		if ((Double)args[0] % 2 == 0)
			res = 1.0;
		return res;
	}
}
