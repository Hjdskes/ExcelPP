package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns true if any argument is true; returns false if all arguments are false.
 * <p>Syntax: =Or(logical1, [logical2], ...)</p>
 * @author Team Awesome
 */
public class Or extends Formula {

	@Override
	public Object getValue(Object... args) throws MathException {
		for(int i = 0; i<args.length; i++) {
			if(args[i] instanceof Boolean) {
				if((boolean) args[i] == true) {
					return true;
				}
			}
			if (!(args[i] instanceof Boolean)) {
				throw new MathException();
			}
			
		}
		return false;
	}
}
