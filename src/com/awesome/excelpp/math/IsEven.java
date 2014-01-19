package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns true if number is even, or false if number is odd. 
 * @author Team Awesome
 */
public class IsEven extends Formula {
	@Override
	public Boolean getValue(Object ... args) throws MathException {
		boolean res = false;
		for (Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			else if (!(o instanceof Double))
				throw new MathException();
			
			if ((Double)o % 2 == 0.0)
				res = true;
		}
		return res;
	}
}
