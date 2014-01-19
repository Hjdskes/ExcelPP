package com.awesome.excelpp.math;


public class Count extends Formula {
	public String getValue(Object... args) {
		return (String.valueOf(args.length));
	}
	
}
