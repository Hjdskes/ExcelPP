package com.awesome.excelpp.math;

public class LogicInt extends Logic  {
	int x;
	int y;
	
	public LogicInt() {
		super();
	}
	
	public double largerThan() {
		double res = 0.0;
		if(x>y)
			res = 1.0;
		return res;
	}
	
	public double smallerThan() {
		double res = 0.0;
		if(x<y)
			res = 1.0;
		return res;
	}

	public double equality() {
		double res = 0.0;
		if(x==y)
			res = 1.0;
		return res;
	}

	@Override
	public double getValue(double... numbers) {
		// TODO Auto-generated method stub
		return 0;
	}

}