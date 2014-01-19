package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Generic base class for all Formulas.
 * <p>All formulas extend this class and override the getValue method to implement their functionality.</p>
 * @author Team Awesome
 */
public abstract class Formula {
	public abstract Object getValue(Object ... args) throws MathException;
}
