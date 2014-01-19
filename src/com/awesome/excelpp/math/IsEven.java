package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class IsEven extends Formula {
	@Override
	public Double getValue(Object ... args) throws MathException {
		double res = 0.0;
		for (Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			
			if (!(o instanceof Double))
				throw new MathException();
			
			if ((Double)o % 2 == 0.0)
				res = 1.0;
		}
		return res;
	}
}
