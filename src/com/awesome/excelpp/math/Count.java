package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The Count function counts the number of cells that contain numbers and counts numbers within the list of arguments.
 * <p>Use the Count function to get the number of entries in a number field that is in a range or array of numbers.
 * </br>Syntax: =Count(value1, [value2], ...)</p>
 * @author Team Awesome
 */
public class Count extends Formula {
	@Override
	public Integer getValue(Object... args) {
		int res = 0;
		for(Object o : args) {
			if (!(o instanceof String && ((String) o).length() == 0)) {
				try {
					getDouble(o);
					res++;
				} catch (MathException e) {
				}
			}
		}
		return res;
	}
}
