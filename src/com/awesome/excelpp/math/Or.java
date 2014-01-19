package com.awesome.excelpp.math;

import java.util.ArrayList;

/**
 * Returns true if any argument is true; returns false if all arguments are false.
 * @author Team Awesome
 */
public class Or {
	public double or(ArrayList<Logic> arguments) {
		double res = 0.0;
		for(int i = 0; i< arguments.size()-1; i++) {
			switch(arguments.get(i).opp) {
			case 0:
				res = ((LogicInt) arguments.get(i)).largerThan();
				break;
			case 1:
				res = ((LogicInt) arguments.get(i)).smallerThan();
				break;
			case 2:
				 res = arguments.get(i).equality();
				break;
			}
		}
		return res;
	}
}
