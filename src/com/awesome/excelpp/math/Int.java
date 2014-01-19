package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Int extends Formula {
	public Integer getValue(Object ... args) throws MathException {
		if (args.length != 1)
			throw new MathException();
		return getInteger(args[0]);
	}
}
