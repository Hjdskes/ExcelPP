package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.RecursionException;

/**
 * Generic base class for all Formulas.
 * <p>All formulas extend this class and override the getValue method to implement their functionality.</p>
 * @author Team Awesome
 */
public abstract class Formula {
	/**
	 * All formulas should override this method to implement functionality.
	 * @param args The arguments passed to the function
	 * @return The result of the evaluation
	 * @throws MathException If the expression was invalid
	 * @throws RecursionException 
	 * @throws ParserException 
	 */
	public abstract Object getValue(Object ... args) throws MathException, ParserException, RecursionException;
	
	/**
	 * Tries to convert the argument to a double. 
	 * @param arg The argument
	 * @return The double value of the argument
	 * @throws MathException If the argument was not a number
	 */
	public double getDouble(Object arg) throws MathException {
		if (arg instanceof Integer)
			return ((Integer)arg).doubleValue();
		if (arg instanceof String && ((String) arg).length() == 0)
			return 0.0;
		if (arg instanceof String) {
			try {
				arg = Double.parseDouble((String)arg);
			} catch (NumberFormatException e) {
			}
		}
		
		if (!(arg instanceof Double))
			throw new MathException();
		
		return (Double)arg;
	}
	
	/**
	 * Tries to convert the argument to an integer. 
	 * @param arg The argument
	 * @return The integer value of the argument
	 * @throws MathException If the argument was not a number
	 */
	public int getInteger(Object arg) throws MathException {
		if (arg instanceof Double)
			return ((Double)arg).intValue();
		if (arg instanceof String) {
			try {
				arg = Integer.parseInt((String)arg);
			} catch (NumberFormatException e) {
			}
		}
		
		if (!(arg instanceof Integer))
			throw new MathException();
		
		return (Integer)arg;
	}
	
	/**
	 * Tries to convert the argument to a String. 
	 * @param arg The argument
	 * @return The String value of the argument
	 * @throws MathException If the argument could not be converted
	 */
	public String getString(Object arg) throws MathException {
		if (arg instanceof Integer)
			return ((Integer)arg).toString();
		if (arg instanceof Double)
			return ((Double)arg).toString();
		if (arg instanceof Boolean)
			return ((Boolean)arg).toString();
		if (!(arg instanceof String))
			throw new MathException();
		return (String)arg;
	}

	/**
	 * Tries to convert the argument to a Boolean.
	 * @param arg The argument
	 * @return The Boolean value of the argument
	 * @throws MathException If the argument could not be converted
	 */
	public Boolean getBoolean(Object arg) throws MathException {
		if (arg instanceof Double)
			return (Double)arg == 0.0 ? false : true;
		if (arg instanceof Integer)
			return (Integer)arg == 0 ? false : true;
		if (arg instanceof String) {
			String res = (String)arg;
			res = res.toLowerCase();
			return res.equals("true");
		}
		if (!(arg instanceof Boolean))
			throw new MathException();
		return (Boolean)arg;
	}
}
