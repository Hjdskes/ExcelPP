package com.awesome.excelpp.math;

public class Power extends Formula {
	public double getValue(double ... vars) {
		return (Math.pow(vars[0], vars[1]));
	}
}
