package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;


public class IsEven extends Formula {
	@Override
	public double getValue(double... numbers) throws MathException {
		if (numbers.length > 1) {
			throw new MathException();
		}
		
		double res = 0.0;
		if (numbers[0] % 2 == 0)
				res = 1.0;
		return res;
	}
}
