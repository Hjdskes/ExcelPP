package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;


public class Upper extends Formula {
	public String getValue(String... vars) {
		String string = vars[0];
		string = string.toUpperCase();
		return string;
	}

	@Override
	public double getValue(Object... args) throws MathException {
		// TODO Auto-generated method stub
		return 0;
	}
}
