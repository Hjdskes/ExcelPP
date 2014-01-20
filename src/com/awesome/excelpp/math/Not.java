package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;


/**
 * Reverses the value of its argument.
 * <p>Use Not when you want to make sure a value is not equal to one particular value.
 * </br>Syntax: =Not(logical);</p>
 * @author Team Awesome
 */
public class Not extends Formula {
	@Override
	public String getValue(Object... args) throws MathException {
		if(args[0] instanceof Boolean) {
			boolean x = (Boolean) args[0];
			return (String.valueOf(!x));
		} else if (args[0] instanceof Double) {
			double ret = 0.0;
			if((double) args[0] == 0.0) {
				ret = 1.0;
			}
			else if((double) args[0] == 1.0) {
				ret = 0.0; 
			}
			else {
				throw new MathException();
			}
			return String.valueOf(ret);
		} else if(args[0] instanceof Integer) {
			int ret = 0;
			if((int) args[0] == 0) {
				ret = 1;
			}
			else if((int) args[0] == 1) {
				ret = 0; 
			}
			else {
				throw new MathException();
			}
			return String.valueOf(ret);
		} else {
			throw new MathException();
		}
	}
}	


