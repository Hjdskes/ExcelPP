package com.awesome.excelpp.math;

public class LogicInt extends Logic  {
	int x;
	int y;
	boolean state;

	public LogicInt() {
		super();
	}
	
	public boolean largerThan() {
		return(x>y);
	}
	
	public boolean smallerThan() {
		return(x<y);
	}

	public boolean equals() {
		return (x==y);
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return false;
	}
}
