package com.awesome.excelpp.stringmath;

import com.awesome.excelpp.math.Formula;

public class Lower extends Formula {
	public String lower() {
		String string = "";
		string.toLowerCase();
		return string;
	}

	@Override
	public double getValue(double... numbers) {
		// TODO Auto-generated method stub
		return 0;
	}
}