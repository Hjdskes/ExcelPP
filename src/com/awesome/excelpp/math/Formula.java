package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Class for the formula parser
 *
 */
public abstract class Formula {
	public abstract String  getValue(Object ... args) throws MathException;
}
