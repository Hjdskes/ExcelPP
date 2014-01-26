package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the result true if value refers to a logical value; otherwise, it returns false.
 * <p>Syntax: =IsLogical(value)</p> 
 * @author Team Awesome
 */
public class IsLogical extends Formula {
	@Override
	public Boolean getValue(Object... args) throws MathException {
		if(args.length != 1)
			throw new MathException();

		if (args[0] instanceof Integer){
			return (int)args[0] == 0 || (int)args[0] == 1;
		}
		
		if (args[0] instanceof Double){
			return (Double)args[0] == 0.0 || (Double)args[0] == 1.0;
		}
		if (args[0] instanceof String) {
			String res = (String)args[0];
			res = res.toLowerCase();
			return res.equals("true") || res.equals("false");
		}
		
		if ((args[0] instanceof Boolean))
			return true;
		
		return false;
	}
}
