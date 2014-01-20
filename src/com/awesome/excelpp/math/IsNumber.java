package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the logical value true if value is a number; otherwise, it returns false.
 * <p>Syntax: =IsNumber(value);</p>
 * @author Team Awesome
 */
public class IsNumber extends Formula {
	@Override
	public Boolean getValue(Object ... args) throws MathException {
		boolean res = true;

		if (args.length != 1)
			throw new MathException();
		else {
			if(args[0] instanceof String) {
				try {
					Double.parseDouble((String)args[0]);
				} catch(Exception e) {
					res = false;
				}
			} else if (!(args[0] instanceof Double) && !(args[0] instanceof Integer))
				res = false;
		}
<<<<<<< HEAD
		else if (!(args[0] instanceof Double) && !(args[0] instanceof Integer))
				res = false;

=======
>>>>>>> 30f48358da5738d636c0a6594b4af051178b5559
		return res;
	}
}
