package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class And extends Formula {
	public String getValue(Object... args) throws MathException {
		double res = 0.0;
		for(int i = 0; i< args.length; i++) {
			System.out.println(args[i]);
/*			switch(arguments.get(i).opp) {
			case 0:
				res = ((LogicInt) arguments.get(i)).largerThan();
				break;
			case 1:
				res = ((LogicInt) arguments.get(i)).smallerThan();
				break;
			case 2:
				 res = arguments.get(i).equality();
				break;
			}*/
		}
		return String.valueOf(res);
	}
}
