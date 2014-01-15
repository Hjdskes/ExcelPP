package com.awesome.excelpp.math;

public class LogicString extends Logic {
	private String x;
	private String y;
	
	public LogicString() {
		super();
	}
	
	public double equality() {
		double res = 0.0;
		if(x.equals(y))
			res = 1.0;
		return res;
	}
}
