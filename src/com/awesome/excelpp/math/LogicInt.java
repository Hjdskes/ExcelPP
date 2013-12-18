package com.awesome.excelpp.math;

public class LogicInt extends Logic {
	int x;
	int y;
	
	public LogicInt() {
		super();
	}
	
	public boolean largerThan() {
		return(x>y);
	}
	
	public boolean smallerThan() {
		return(x<y);
	}

	@Override
	public boolean equals() {
		return (x==y);
	}
}
