package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Generic base class for all Formulas.
 * <p>All formulas extend this class and override the getValue method to implement their functionality.</p>
 * @author Team Awesome
 */
public abstract class Formula {
	public abstract Object getValue(Object ... args) throws MathException;
	
	public double getDouble(Object arg) throws MathException {
		if (arg instanceof Integer)
			arg = ((Integer)arg).doubleValue();
		if (!(arg instanceof Double))
			throw new MathException();
		return (Double)arg;
	}
	
	public int getInteger(Object arg) throws MathException {
		if (arg instanceof Double)
			arg = ((Double)arg).intValue();
		if (!(arg instanceof Integer))
			throw new MathException();
		return (Integer)arg;
	}
	
	public String getString(Object arg) throws MathException {
		if (arg instanceof Integer)
			arg = ((Integer)arg).toString();
		if (arg instanceof Double)
			arg = ((Double)arg).toString();
		if (!(arg instanceof String))
			throw new MathException();
		return (String)arg;
	}
}
