package com.awesome.excelpp.math;

public class IsNumber extends Formula {
	public Boolean getValue(Object ... args) {
		boolean res = true;
		for (Object o : args) {
			if (!(o instanceof Double) && !(o instanceof Integer))
				res = false;
		}
		return res;
	}
}
