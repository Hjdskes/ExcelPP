package com.awesome.excelpp.math;


public class Count extends Formula {
	public double getValue(Object... args) {
		/* double count = 0;
		for(int i=0; i < args.length; i++) {
			count = i;
		}*/
		return (args.length);
	}
	
}
