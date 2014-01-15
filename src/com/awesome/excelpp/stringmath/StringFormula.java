package com.awesome.excelpp.stringmath;

import com.awesome.excelpp.math.exception.MathException;

public abstract class StringFormula {
	public abstract String getValue(String ... vars) throws MathException;
}
