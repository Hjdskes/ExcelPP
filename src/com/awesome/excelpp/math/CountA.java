package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The CountA function counts the number of cells that are not empty in a range.
 * <p>Syntax: =CountA(value1, [value2], ...)</p>
 * @author Team Awesome
 */
public class CountA extends Formula {
	@Override
	public Integer getValue(Object... args) throws MathException {
		int count = 0;
		for(Object o : args) {
			if(o instanceof Double) {
				if((double) o !=  0.0)
					count++;
			} else if(o instanceof String) {
				if((String) o !=  "")
					count++;
			} else if(o instanceof Integer) {
				if((int) o !=  0)
					count++;
			} else if(o instanceof Boolean) {
				if((Boolean) o == true || (Boolean) o == false)
					count++;
			}
		}
		return count;
	}
}
