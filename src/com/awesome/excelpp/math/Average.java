package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the average (arithmetic mean) of the arguments.
 * <p>For example, if the range A1:A20 contains numbers, the formula =Average(A1:A20)
 * returns the average of those numbers.</p>
 * @author Team Awesome
 */
public class Average extends Formula {
	public Double getValue(Object ... args) throws MathException {
		double sum = 0;
		int total = 0;

		for (Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			else if (!(o instanceof Double))
				throw new MathException();
			
			sum += (Double)o;
			total++;
		}
		return sum / total;	
	}
}
