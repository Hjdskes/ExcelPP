package com.awesome.excelpp.math;

/**
 * The Count function counts the number of cells that contain numbers and counts numbers within the list of arguments.
 * <p>Use the Count function to get the number of entries in a number field that is in a range or array of numbers.
 * </br>Syntax: =Count(value1, [value2], ...)</p>
 * @author Team Awesome
 */
public class Count extends Formula {
	@Override
	public Double getValue(Object... args) {
		double res = 0.0;
		for(Object o : args) {
			if(o instanceof Double) {
				if((double) o !=  0.0)
	 				res++;
			} else if (o instanceof Integer) {
				if((int) o != 0)
					res++;
			}
		}
		return res;
	}
}
